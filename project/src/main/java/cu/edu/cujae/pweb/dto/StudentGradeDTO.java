package cu.edu.cujae.pweb.dto;

public class StudentGradeDTO {

    private int studentId;
    private int gradeValue;
    private int subjectId;
    private int courseId;
    private int yearId;

    public StudentGradeDTO(int studentId, int gradeValue, int subjectId, int courseId, int yearId) {
        this.studentId = studentId;
        this.gradeValue = gradeValue;
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.yearId = yearId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
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
}
