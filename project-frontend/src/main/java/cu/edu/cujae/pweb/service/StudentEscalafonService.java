package cu.edu.cujae.pweb.service;

import cu.edu.cujae.pweb.dto.StudentEscalafonDTO;

import java.util.List;

public interface StudentEscalafonService {
    List<StudentEscalafonDTO> getStudentsEscalafon();
    StudentEscalafonDTO getStudentEscalafonById(String id);
}
