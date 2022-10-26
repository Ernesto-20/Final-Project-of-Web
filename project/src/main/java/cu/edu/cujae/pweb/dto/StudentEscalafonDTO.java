package cu.edu.cujae.pweb.dto;

public class StudentEscalafonDTO {

    private String id;
    int escalafonNum;
    private int listNum;
    private String firstName;
    private String lastName;
    private String gender;
    private String municipality;
    private double gradesAverage;

    public StudentEscalafonDTO() {
        super(); // TODO Auto-generated constructor stub
    }

    public StudentEscalafonDTO(
        String id,
        int escalafonNum,
        int listNum,
        String firstName,
        String lastName,
        String gender,
        String municipality,
        double gradesAverage
    ) {
        super();
        this.id = id;
        this.escalafonNum = escalafonNum;
        this.listNum = listNum;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.municipality = municipality;
        this.gradesAverage = gradesAverage;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public int getEscalafonNum() {
        return escalafonNum;
    }
    public void setEscalafonNum(int escalafonNum) {
        this.escalafonNum = escalafonNum;
    }
    public int getListNum() {
        return listNum;
    }
    public void setListNum(int listNum) {
        this.listNum = listNum;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getMunicipality() {
        return municipality;
    }
    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }
    public double getGradesAverage() { return gradesAverage; }
    public void setGradesAverage(double gradesAverage) { this.gradesAverage = gradesAverage; }
}
