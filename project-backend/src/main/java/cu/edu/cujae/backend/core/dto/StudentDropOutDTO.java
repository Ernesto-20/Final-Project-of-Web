package cu.edu.cujae.backend.core.dto;

public class StudentDropOutDTO {

    private Integer studentId;
    private Integer courseId;
    private Integer dropoutId;
    
    public StudentDropOutDTO() {
		super();
	}

	public StudentDropOutDTO(Integer dropoutId, Integer courseId, Integer studentId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.dropoutId = dropoutId;
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
