package cu.edu.cujae.pweb.dto;

public class DropOutDTO {

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
