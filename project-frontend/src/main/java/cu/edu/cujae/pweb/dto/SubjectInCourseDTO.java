package cu.edu.cujae.pweb.dto;

public class SubjectInCourseDTO {

    private String subjectId;
    private String courseId;
    private String yearId;
    private int hoursLong;

    public SubjectInCourseDTO() {
        super();
    }
    public SubjectInCourseDTO(String subjectId, String courseId, String yearId, int hoursLong) {
        super();
        this.subjectId = subjectId;
        this.courseId = courseId;
        this.yearId = yearId;
        this.hoursLong = hoursLong;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getYearId() {
        return yearId;
    }

    public void setYearId(String yearId) {
        this.yearId = yearId;
    }

    public int getHoursLong() {
        return hoursLong;
    }

    public void setHoursLong(int hoursLong) {
        this.hoursLong = hoursLong;
    }

}
