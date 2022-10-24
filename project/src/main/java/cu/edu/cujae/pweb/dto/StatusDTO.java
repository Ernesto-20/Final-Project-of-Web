package cu.edu.cujae.db_subject1.dto;

import cu.edu.cujae.db_subject1.utils.IDTO;

public class StatusDTO implements IDTO {
    private Integer id;
    private String description;

    public StatusDTO(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
