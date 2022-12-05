package cu.edu.cujae.pweb.dto;

public class StudentInBrigadeNamedDTO {

    private int studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private int brigadeId;
    private String brigadeName;
    private Integer numberScale;

    public StudentInBrigadeNamedDTO(int studentId, String studentName, int courseId, String courseName, int brigadeId, String brigadeName, Integer numberScale) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.brigadeId = brigadeId;
        this.brigadeName = brigadeName;
        this.numberScale = numberScale;
    }

    public StudentInBrigadeNamedDTO(int studentId, int courseId, int brigadeId) {
        this.studentId = studentId;
        this.studentName = null;
        this.courseId = courseId;
        this.courseName = null;
        this.brigadeId = brigadeId;
        this.brigadeName = null;
        this.numberScale = 245;
    }

    public StudentInBrigadeNamedDTO(StudentInBrigadeDTO dto) {
        this.studentId = dto.getStudentId();
        this.studentName = null;
        this.courseId = dto.getCourseId();
        this.courseName = null;
        this.brigadeId = dto.getBrigadeId();
        this.brigadeName = null;
        this.numberScale = dto.getNumberScale();
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

    public String getBrigadeName() {
        return brigadeName;
    }

    public void setBrigadeName(String brigadeName) {
        this.brigadeName = brigadeName;
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
