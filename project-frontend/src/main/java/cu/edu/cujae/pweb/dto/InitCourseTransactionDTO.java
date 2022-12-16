package cu.edu.cujae.pweb.dto;

import java.util.List;

public class InitCourseTransactionDTO {
    List<SubjectInCourseCompleteDTO> subjectInCourseCompleteDTO;
    List<List<StudentDTO>> newStudents;

    public InitCourseTransactionDTO(List<SubjectInCourseCompleteDTO> subjectInCourseCompleteDTO, List<List<StudentDTO>> newStudents) {
        this.subjectInCourseCompleteDTO = subjectInCourseCompleteDTO;
        this.newStudents = newStudents;
    }

    public List<SubjectInCourseCompleteDTO> getSubjectInCourseCompleteDTO() {
        return subjectInCourseCompleteDTO;
    }

    public void setSubjectInCourseCompleteDTO(List<SubjectInCourseCompleteDTO> subjectInCourseCompleteDTO) {
        this.subjectInCourseCompleteDTO = subjectInCourseCompleteDTO;
    }

    public List<List<StudentDTO>> getNewStudents() {
        return newStudents;
    }

    public void setNewStudents(List<List<StudentDTO>> newStudents) {
        this.newStudents = newStudents;
    }
}
