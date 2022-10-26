package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.SubjectInCourseWithNameDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SubjectInCourseWithNameServiceImpl implements SubjectInCourseWithNameService{
    @Override
    public List<SubjectInCourseWithNameDTO> getSubjects() {

        List<SubjectInCourseWithNameDTO> subjects = new ArrayList<>();
        subjects.add(new SubjectInCourseWithNameDTO(UUID.randomUUID().toString(), "Cálculo 1", 48));
        subjects.add(new SubjectInCourseWithNameDTO(UUID.randomUUID().toString(), "Introducción a la Programación", 58));
        subjects.add(new SubjectInCourseWithNameDTO(UUID.randomUUID().toString(), "Matemática Discreta", 32));
        subjects.add(new SubjectInCourseWithNameDTO(UUID.randomUUID().toString(), "Historia de Cuba", 4));
        subjects.add(new SubjectInCourseWithNameDTO(UUID.randomUUID().toString(), "Diseño de Interfaces y Pruebas", 22));
        subjects.add(new SubjectInCourseWithNameDTO(UUID.randomUUID().toString(), "Matemática Computacional", 36));

        return subjects;
    }

    @Override
    public SubjectInCourseWithNameDTO getSubjectById(String id) {
        return getSubjects().stream().filter(subject -> subject.getId().equals(id)).findFirst().get();
    }

    @Override
    public void createStudent(SubjectInCourseWithNameDTO subject) {

    }

    @Override
    public void updateStudent(SubjectInCourseWithNameDTO subject) {

    }

    @Override
    public void deleteStudent(String id) {
    }
}
