package cu.edu.cujae.backend.core.dto;

public class StudentGradeOnlyIdDTO {

    private Integer yearId;
    private Integer studentId;
    private Integer subjectId;
    private Integer courseId;
    private Integer gradeValue;

    public StudentGradeOnlyIdDTO(){}

    public StudentGradeOnlyIdDTO(Integer yearId, Integer studentId, Integer subjectId, Integer courseId, Integer gradeValue) {
        this.yearId = yearId;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.gradeValue = gradeValue;
    }

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }
}
