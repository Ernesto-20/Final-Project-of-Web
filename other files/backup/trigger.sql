/*
CREATE OR REPLACE FUNCTION public.sumar_numero(integer) 
RETURNS integer AS
$BODY$
DECLARE
sum integer;
BEGIN
sum = 9 + $1;
RETURN sum;
END;
$BODY$
LANGUAGE plpgsql
*/

--SELECT public.sumar_numero(5);


/*
CREATE OR REPLACE FUNCTION public.find_finished_course()
RETURNS integer AS
$BODY$
DECLARE
course_amount integer;
finished_course integer;
BEGIN
course_amount = (SELECT COUNT("course".id) FROM "course");
IF course_amount > 1 THEN
	finished_course = (SELECT "course".id FROM "course"
	LIMIT 1 OFFSET ((SELECT COUNT("course".id) FROM "course") - 2));
	RETURN finished_course;
ELSE
	RETURN (SELECT "course".id FROM "course" LIMIT 1);
END IF;
END;
$BODY$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION public.find_students_from_last_course(refcursor)
RETURNS refcursor AS
$BODY$
DECLARE
finished_course integer;
BEGIN
finished_course = (SELECT public.find_finished_course());
OPEN $1 FOR SELECT "student_in_brigade".student_id FROM "student_in_brigade"
	WHERE "student_in_brigade".course_id = finished_course;
RETURN $1;
END;
$BODY$
LANGUAGE plpgsql;



CREATE OR REPLACE FUNCTION public.did_student_pass(std_id integer, crs_id integer)
RETURNS integer AS
$BODY$
DECLARE
--grades refcursor;
pass integer;
grade record;
BEGIN
pass = 1;
--OPEN grades FOR SELECT "student_grade".grade_value FROM "student_grade" WHERE "student_grade".student_id = std_id AND "student_grade".course_id = crs_id;
FOR grade IN SELECT "student_grade".grade_value FROM "student_grade" WHERE "student_grade".student_id = std_id AND "student_grade".course_id = crs_id
	LOOP
		IF grade.grade_value = 2 THEN
			pass = 0;
		END IF;
	END LOOP;
RETURN pass;
END;
$BODY$
LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION public.find_year_of_student_in_course(std_id integer, crs_id integer)
RETURNS integer AS
$BODY$
DECLARE
_year integer;
BEGIN
_year = (SELECT "brigade".year_id FROM "brigade", "student_in_brigade"
	WHERE "brigade".id = "student_in_brigade".brigade_id
	AND "student_in_brigade".student_id = std_id
	AND "student_in_brigade".course_id = crs_id
	LIMIT 1); --The result should be a single id really. Limit should not be necessary
RETURN _year;
END;
$BODY$
LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION public.find_if_brigade_exists_in_next_year(_num integer, _year integer)
RETURNS integer AS
$BODY$
DECLARE
brigade_exists integer;
BEGIN
brigade_exists = (SELECT COUNT(*) FROM brigade WHERE brigade."number" = _num AND brigade.year_id = _year);
RETURN brigade_exists;
END;
$BODY$
LANGUAGE plpgsql;

*/



--Se viene la tiza molida
CREATE OR REPLACE FUNCTION update_student_situation_after_course_ends()
RETURNS TRIGGER AS
$BODY$
DECLARE
courses_amount integer;
finished_course integer; --its id
new_course integer;
passed integer;
std_finished_year integer;
_student record;
finished_brigade record;
new_brigade record;
BEGIN
courses_amount = (SELECT COUNT(*) FROM "course");
IF courses_amount > 1 THEN

	finished_course = public.find_finished_course();
	new_course = (SELECT course.id FROM course ORDER BY course.id DESC LIMIT 1);

	FOR _student IN (SELECT "student_in_brigade".student_id FROM "student_in_brigade"
	WHERE "student_in_brigade".course_id = finished_course)
		LOOP

			IF NOT ((SELECT student.status_id FROM student WHERE student.id = _student.student_id LIMIT 1) = 4) THEN


				SELECT brigade.id, brigade."number", brigade.year_id INTO finished_brigade
				FROM brigade INNER JOIN student_in_brigade ON brigade.id = student_in_brigade.brigade_id
				WHERE student_in_brigade.student_id = _student.student_id AND student_in_brigade.course_id = finished_course
				LIMIT 1; --LIMIT 1 just in case

				--Find out if the student passed the year
				passed = public.did_student_pass(_student.student_id, finished_course);

				IF passed = 1 THEN--Passed

					std_finished_year = public.find_year_of_student_in_course(_student.student_id, finished_course);

					IF std_finished_year = 1 OR std_finished_year = 2 OR std_finished_year = 3 THEN

						IF public.find_if_brigade_exists_in_next_year(finished_brigade."number", (finished_brigade.year_id + 1)) = 0 THEN --Brigade in next year doesn't exist

							INSERT INTO brigade ("number", year_id) VALUES (finished_brigade."number", (finished_brigade.year_id + 1));

						END IF;

						SELECT * INTO new_brigade FROM brigade WHERE brigade."number" = finished_brigade."number" AND brigade.year_id = (finished_brigade.year_id + 1);

						INSERT INTO student_in_brigade VALUES (_student.student_id, new_course, new_brigade.id);
						UPDATE student SET status_id = 2 WHERE student.id = _student.student_id;
						

					ELSIF std_finished_year = 4 THEN


						UPDATE student SET status_id = 5 WHERE student.id = _student.student_id;
						INSERT INTO student_dropout VALUES (3, finished_course, _student.student_id);
						
					END IF;
					

				ELSE--Failed

					IF (SELECT student.status_id FROM student WHERE student.id = _student.student_id LIMIT 1) = 3 THEN --Student was already repeating the year

						UPDATE student SET status_id = 4 WHERE student.id = _student.student_id;
						INSERT INTO student_dropout VALUES (4, finished_course, _student.student_id);

					ELSE

						UPDATE student SET status_id = 3 WHERE student.id = _student.student_id;
						INSERT INTO student_in_brigade VALUES (_student.student_id, new_course, finished_brigade.id);

					END IF; 

				END IF;

			END IF;

		END LOOP;
	
ELSE --This wil be the first course. There is nothing to be done
END IF;
RETURN NULL;
END;
$BODY$
LANGUAGE plpgsql;


CREATE TRIGGER update_student_situation AFTER INSERT ON course FOR EACH ROW EXECUTE PROCEDURE update_student_situation_after_course_ends();












