package cu.edu.cujae.pweb.dto;

public class StudentDropOutNamedDTO {

    private Integer studentId;
    private String studentName;
    private Integer courseId;
    private String courseName;
    private Integer dropoutId;
    private String dropoutName;

    public StudentDropOutNamedDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentDropOutNamedDTO(Integer studentId, String studentName, Integer courseId, String courseName, Integer dropoutId, String dropoutName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.dropoutId = dropoutId;
        this.dropoutName = dropoutName;
    }

    public StudentDropOutNamedDTO(Integer studentId, Integer courseId, Integer dropoutId) {
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

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getDropoutId() {
        return dropoutId;
    }

    public void setDropoutId(Integer dropoutId) {
        this.dropoutId = dropoutId;
    }

}
