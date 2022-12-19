package cu.edu.cujae.backend.core.dto;

public class SubjectInCourseCompleteDTO {
    private CourseDTO courseDTO;
    private Integer yearID;
    private Integer amountHours;
    private SubjectDTO subjectDTO;

    public SubjectInCourseCompleteDTO(CourseDTO courseDTO, Integer yearID, Integer amountHours, SubjectDTO subjectDTO) {
        this.courseDTO = courseDTO;
        this.yearID = yearID;
        this.amountHours = amountHours;
        this.subjectDTO = subjectDTO;
    }

    public Integer getAmountHours() {
        return amountHours;
    }

    public void setAmountHours(Integer amountHours) {
        this.amountHours = amountHours;
    }

    public CourseDTO getCourseDTO() {
        return courseDTO;
    }

    public void setCourseDTO(CourseDTO courseDTO) {
        this.courseDTO = courseDTO;
    }

    public Integer getYearID() {
        return yearID;
    }

    public void setYearID(Integer yearID) {
        this.yearID = yearID;
    }

    public SubjectDTO getSubjectDTO() {
        return subjectDTO;
    }

    public void setSubjectDTO(SubjectDTO subjectDTO) {
        this.subjectDTO = subjectDTO;
    }
}
