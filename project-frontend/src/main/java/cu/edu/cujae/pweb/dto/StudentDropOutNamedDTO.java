package cu.edu.cujae.pweb.dto;

public class StudentDropOutNamedDTO {

    private int studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private int dropoutId;
    private String dropoutName;

    public StudentDropOutNamedDTO(int studentId, String studentName, int courseId, String courseName, int dropoutId, String dropoutName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.dropoutId = dropoutId;
        this.dropoutName = dropoutName;
    }

    public StudentDropOutNamedDTO(int studentId, int courseId, int dropoutId) {
        this.studentId = studentId;
        this.studentName = null;
        this.courseId = courseId;
        this.courseName = null;
        this.dropoutId = dropoutId;
        this.dropoutName = null;
    }

    public StudentDropOutNamedDTO(StudentDropOutDTO dto){
        this.studentId = dto.getStudentId();
        this.studentName = null;
        this.courseId = dto.getCourseId();
        this.courseName = null;
        this.dropoutId = dto.getDropoutId();
        this.dropoutName = null;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDropoutName() {
        return dropoutName;
    }

    public void setDropoutName(String dropoutName) {
        this.dropoutName = dropoutName;
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
