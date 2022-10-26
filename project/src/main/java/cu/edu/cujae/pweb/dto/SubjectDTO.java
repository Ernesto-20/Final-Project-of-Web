package cu.edu.cujae.pweb.dto;

public class SubjectDTO {

    private String id;
    private String name;
    private boolean newRecord;

    public SubjectDTO() {
        super();
    }
    public SubjectDTO(String id, String name, boolean newRecord) {
        super();
        this.id = id;
        this.name = name;
        this.newRecord = newRecord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }
}
