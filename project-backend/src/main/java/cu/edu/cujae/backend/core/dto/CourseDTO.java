package cu.edu.cujae.backend.core.dto;

public class CourseDTO {

    private Integer id;
    private int start;
    private int finish;
    private String identifier;

    public CourseDTO(Integer id, int start, int finish) {
        this.id = id;
        this.start = start;
        this.finish = finish;
        this.identifier = start + "-" + finish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getFinish() {
        return finish;
    }

    public void setFinish(int finish) {
        this.finish = finish;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString(){
        return start+"-"+finish;
    }

}
