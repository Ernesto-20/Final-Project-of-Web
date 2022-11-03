package cu.edu.cujae.pweb.dto;

public class CourseDTO {

    private String id;
    private int start;
    private int finish;
    private String identifier;
    private boolean newRecord;

    public CourseDTO() {
		super(); // TODO Auto-generated constructor stub
	}
    
    public CourseDTO(String id, int start, int finish, boolean newRecord) {
    	super();
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.identifier = start + "-" + finish;
        this.newRecord = newRecord;
    }

    public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
        return id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString(){
        return start+"-"+finish;
    }

}
