package cu.edu.cujae.pweb.dto;

public class CourseDTO {

    private Integer id;
    private int start;
    private int finish;
    private String identifier;

    public CourseDTO() {
		super(); // TODO Auto-generated constructor stub
	}
    
    public CourseDTO(Integer id, int start, int finish) {
    	super();
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.identifier = start + "-" + finish;
    }

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
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
