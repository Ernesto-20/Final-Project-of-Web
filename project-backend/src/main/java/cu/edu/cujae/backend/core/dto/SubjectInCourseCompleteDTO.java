package cu.edu.cujae.backend.core.dto;

public class SubjectInCourseCompleteDTO {
    private CourseDTO courseDTO;
    private String yearID;
    private Integer amountHours;
    private SubjectDTO subjectDTO;

    public SubjectInCourseCompleteDTO(CourseDTO courseDTO, String yearID, Integer amountHours, SubjectDTO subjectDTO) {
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
