package cu.edu.cujae.pweb.dto;

public class SubjectInCourseNamedDTO {

    private String subjectId;
    private String subjectName;
    private String courseId;
    private String courseName;
    private String yearId;
    private int hoursLong;

    public SubjectInCourseNamedDTO(){ super(); }

    public SubjectInCourseNamedDTO(String subjectId, String subjectName, String courseId, String courseName, String yearId, int hoursLong) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.yearId = yearId;
        this.hoursLong = hoursLong;
    }

    public SubjectInCourseNamedDTO(String subjectId, String courseId, String yearId, int hoursLong) {
        this.subjectId = subjectId;
        this.subjectName = null;
        this.courseId = courseId;
        this.courseName = null;
        this.yearId = yearId;
        this.hoursLong = hoursLong;
    }

    public SubjectInCourseNamedDTO(SubjectInCourseDTO dto){
        this.subjectId = dto.getSubjectId();
        this.subjectName = null;
        this.courseId = dto.getCourseId();
        this.courseName = null;
        this.yearId = dto.getYearId();
        this.hoursLong = dto.getHoursLong();
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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
