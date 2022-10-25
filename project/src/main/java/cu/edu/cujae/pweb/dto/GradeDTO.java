package cu.edu.cujae.pweb.dto;


public class GradeDTO {

    private int value;
    private String scale;

    public GradeDTO(int value, String scale) {
        this.value = value;
        this.scale = scale;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

}
