package cu.edu.cujae.db_subject1.dto;

import cu.edu.cujae.db_subject1.utils.IDTO;

public class DropOutDTO implements IDTO {

    private Integer id;
    private String cause;

    public DropOutDTO(Integer id, String cause) {
        this.id = id;
        this.cause = cause;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

}
