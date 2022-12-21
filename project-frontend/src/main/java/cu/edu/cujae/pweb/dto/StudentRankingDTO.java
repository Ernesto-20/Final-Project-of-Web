package cu.edu.cujae.pweb.dto;

public class StudentRankingDTO {

    private int studentId;
    private String studentName;
    private double average;

    public StudentRankingDTO(){}

    public StudentRankingDTO(int studentId, String studentName, double average){
        this.studentId = studentId;
        this.studentName = studentName;
        this.average = average;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }
}
