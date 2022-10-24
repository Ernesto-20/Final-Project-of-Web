package cu.edu.cujae.db_subject1.dto;

import cu.edu.cujae.db_subject1.utils.IDTO;

public class SubjectInCourseDTO implements IDTO {

    private int subjectId;
    private int courseId;
    private int yearId;
    private int hoursLong;

    public SubjectInCourseDTO(int subjectId, int courseId, int yearId, int hoursLong) {
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.yearId = yearId;
        this.hoursLong = hoursLong;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    public int getHoursLong() {
        return hoursLong;
    }

    public void setHoursLong(int hoursLong) {
        this.hoursLong = hoursLong;
    }

}
