package cu.edu.cujae.backend.core.dto;

public class SubjectInCourseNamedDTO {

    private int subjectId;
    private String subjectName;
    private int courseId;
    private String courseName;
    private int yearId;
    private int hoursLong;

    public SubjectInCourseNamedDTO(int subjectId, String subjectName, int courseId, String courseName, int yearId, int hoursLong) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.yearId = yearId;
        this.hoursLong = hoursLong;
    }

    public SubjectInCourseNamedDTO(int subjectId, int courseId, int yearId, int hoursLong) {
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

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getYearId() {
        return yearId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    public int getHoursLong() {
        return hoursLong;
    }

    public void setHoursLong(int hoursLong) {
        this.hoursLong = hoursLong;
    }

}
