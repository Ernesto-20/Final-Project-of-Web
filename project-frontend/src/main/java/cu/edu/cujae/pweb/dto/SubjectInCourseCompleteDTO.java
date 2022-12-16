package cu.edu.cujae.pweb.dto;

public class SubjectInCourseCompleteDTO {
    private CourseDTO courseDTO;
    private String yearID;
    private SubjectDTO subjectDTO;

    public SubjectInCourseCompleteDTO(CourseDTO courseDTO, String yearID, SubjectDTO subjectDTO) {
        this.courseDTO = courseDTO;
        this.yearID = yearID;
        this.subjectDTO = subjectDTO;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public String getYearID() {
        return yearID;
    }

    public void setYearID(String yearID) {
        this.yearID = yearID;
    }

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }
}
