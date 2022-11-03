package cu.edu.cujae.backend.core.dto;

public class StudentDropOutDTO {

    private int studentId;
    private int courseId;
    private int dropoutId;

    public StudentDropOutDTO(int dropoutId, int courseId, int studentId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.dropoutId = dropoutId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getDropoutId() {
        return dropoutId;
    }

    public void setDropoutId(int dropoutId) {
        this.dropoutId = dropoutId;
    }

}
