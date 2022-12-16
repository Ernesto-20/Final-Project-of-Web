package cu.edu.cujae.backend.core.dto;

public class DropOutDTO {

    private Integer id;
    private String cause;

    public DropOutDTO() {
		super();
	}
    
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
