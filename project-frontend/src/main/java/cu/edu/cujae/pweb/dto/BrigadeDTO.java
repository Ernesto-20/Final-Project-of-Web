package cu.edu.cujae.pweb.dto;

public class BrigadeDTO {

    private Integer id;
    private Integer yearId;
    private int number;
    private String name;

    public BrigadeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BrigadeDTO(Integer id, int number, Integer yearId) {
        this.id = id;
        this.number = number;
        this.yearId = yearId;
        name = "" + yearId + "" + number;
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

    public Integer getYearId() {
        return yearId;
    }

    public void setYearId(Integer yearId) {
        this.yearId = yearId;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString(){
        return number+"";
    }

	public void setName(String name) {
		this.name = name;
	}
}
