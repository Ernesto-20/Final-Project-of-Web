package cu.edu.cujae.backend.core.dto;

public class YearDTO {
    //Id is not serial, but the actual year
    private int id;

    public YearDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return id+"";
    }
}
