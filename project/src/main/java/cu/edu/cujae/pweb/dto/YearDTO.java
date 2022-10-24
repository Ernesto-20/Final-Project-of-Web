package cu.edu.cujae.db_subject1.dto;

import cu.edu.cujae.db_subject1.utils.IDTO;

public class YearDTO implements IDTO {

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
