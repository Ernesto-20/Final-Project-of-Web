package cu.edu.cujae.pweb.dto;

public class StudentGradeCourseIdDTO {

    private Integer yearId;
    private Integer studentId;
    private String studentName;
    private Integer subjectId;
    private String subject;
    private Integer courseId;
    private Integer gradeValue;

    public StudentGradeCourseIdDTO() {
        super(); // TODO Auto-generated constructor stub
    }

    public StudentGradeCourseIdDTO(Integer yearId, Integer studentId, String studentName, Integer subjectId,
                                   String subject, Integer courseId, Integer gradeValue) {
        super();
        this.yearId = yearId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.subjectId = subjectId;
        this.subject = subject;
        this.courseId = courseId;
        this.gradeValue = gradeValue;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(Integer gradeValue) {
        this.gradeValue = gradeValue;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}
