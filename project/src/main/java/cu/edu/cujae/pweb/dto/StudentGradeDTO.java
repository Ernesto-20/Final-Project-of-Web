package cu.edu.cujae.pweb.dto;


public class StudentGradeDTO {

	private String id;
    private String studentId;
    // private int studentId;
    private String courseId;
    // private int courseId;
    private int yearId;
    private String subjectId;
    // private int subjectId;
    private int gradeValue;
    private boolean newRecord;

    public StudentGradeDTO() {
		super(); // TODO Auto-generated constructor stub
	}
    
    public StudentGradeDTO(String id, String studentId, String courseId, int yearId, String subjectId, int gradeValue, boolean newRecord) {
    	super();
		this.id = id;
    	this.studentId = studentId;
        this.courseId = courseId;
        this.yearId = yearId;
        this.subjectId = subjectId;
        this.gradeValue = gradeValue;
        this.newRecord = newRecord;
    }
    // public StudentGradeDTO(String id, int studentId, int courseId, int yearId, int subjectId, int gradeValue, boolean newRecord) {
    // 	super();
	// 	this.id = id;
    // 	this.studentId = studentId;
    //     this.courseId = courseId;
    //     this.yearId = yearId;
    //     this.subjectId = subjectId;
    //     this.gradeValue = gradeValue;
    //     this.newRecord = newRecord;
    // }

    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    // public void setStudentId(int studentId) {
    //     this.studentId = studentId;
    // }

    // public int getCourseId() {
    //     return courseId;
    // }

    // public void setCourseId(int courseId) {
    //     this.courseId = courseId;
    // }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }
   
    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }
    // public int getSubjectId() {
    //     return subjectId;
    // }

    // public void setSubjectId(int subjectId) {
    //     this.subjectId = subjectId;
    // }
    
    public int getGradeValue() {
        return gradeValue;
    }

    public void setGradeValue(int gradeValue) {
        this.gradeValue = gradeValue;
    }

    public boolean isNewRecord() {
		return newRecord;
	}
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
