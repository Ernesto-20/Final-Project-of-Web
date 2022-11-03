package cu.edu.cujae.pweb.dto;

public class YearDTO {

    //Id is not serial, but the actual year
    private int id;
    private boolean newRecord;

   
    public YearDTO() {
    	super();
    }
	public YearDTO(int id, boolean newRecord) {
		super();
        this.id = id;
        this.newRecord = newRecord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public boolean isNewRecord() {
		return newRecord;
	}

	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}


    @Override
    public String toString(){
        return id+"";
    }

}
