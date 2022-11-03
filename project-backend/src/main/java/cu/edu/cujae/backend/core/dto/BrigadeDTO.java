package cu.edu.cujae.backend.core.dto;

public class BrigadeDTO {

    private Integer id;
    private int number;
    private int yearId;

    public BrigadeDTO(Integer id, int number, int yearId) {
        this.id = id;
        this.number = number;
        this.yearId = yearId;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    @Override
    public String toString(){
        return number+"";
    }
}
