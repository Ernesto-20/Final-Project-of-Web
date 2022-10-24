package cu.edu.cujae.db_subject1.dto;

import cu.edu.cujae.db_subject1.utils.IDTO;

public class StudentDropOutDTO implements IDTO {

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
