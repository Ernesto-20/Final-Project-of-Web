package cu.edu.cujae.backend.core.dto;

public class SubjectDTO {

    private Integer id;
    private String name;
    private boolean newRecord;

    public SubjectDTO() {
        super();
    }

    public SubjectDTO(Integer id, String name, boolean newRecord) {
        super();
        this.id = id;
        this.name = name;
        this.newRecord = newRecord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
