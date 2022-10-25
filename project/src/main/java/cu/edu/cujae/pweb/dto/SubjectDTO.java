package cu.edu.cujae.pweb.dto;

public class SubjectDTO {

    private Integer id;
    private boolean newRecord;
    private String name;

    public SubjectDTO() {
        super();
    }
    public SubjectDTO(Integer id, String name) {
        this.id = id;
        this.newRecord = newRecord;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNewRecord() {
        return newRecord;
    }

    public void setNewRecord(boolean newRecord) {
        this.newRecord = newRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
