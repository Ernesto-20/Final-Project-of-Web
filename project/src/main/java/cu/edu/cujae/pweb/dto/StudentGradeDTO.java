package cu.edu.cujae.pweb.dto;


public class StudentGradeDTO {

	private String id;
    private int studentId;
    private int gradeValue;
    private int subjectId;
    private int courseId;
    private int yearId;
    private boolean newRecord;

    public StudentGradeDTO() {
		super(); // TODO Auto-generated constructor stub
	}
    
    public StudentGradeDTO(String id, int studentId, int gradeValue, int subjectId, int courseId, int yearId, boolean newRecord) {
    	super();
		this.id = id;
    	this.studentId = studentId;
        this.gradeValue = gradeValue;
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.yearId = yearId;
        this.newRecord = newRecord;
    }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
    public boolean isNewRecord() {
		return newRecord;
	}
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
