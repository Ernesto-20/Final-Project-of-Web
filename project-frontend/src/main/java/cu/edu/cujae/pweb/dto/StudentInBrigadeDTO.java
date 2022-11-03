package cu.edu.cujae.pweb.dto;

public class StudentInBrigadeDTO {

    private int studentId;
    private int courseId;
    private int brigadeId;
    private Integer numberScale;

    public StudentInBrigadeDTO(int studentId, int courseId, int brigadeId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.brigadeId = brigadeId;
    }

    public StudentInBrigadeDTO(int studentId, int courseId, int brigadeId, int numberScale) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.brigadeId = brigadeId;
        this.numberScale = numberScale;
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

    public int getBrigadeId() {
        return brigadeId;
    }

    public void setBrigadeId(int brigadeId) {
        this.brigadeId = brigadeId;
    }

    public int getNumberScale() {
        return numberScale;
    }

    public void setNumberScale(int numberScale) {
        this.numberScale = numberScale;
    }
}
