// package cu.edu.cujae.pweb.dto;
//
// import java.util.Map;
//
// public class StudentGradeDTO {
//
// private Integer id;
// private String studentName;
//
// // "subjectsGrades" se utiliza para mostrar las notas de las asignaturas si
// se
// // conoce el año
// // <Subject, Grade>
// private Map<String, Integer> subjectsGrades;
//
// // "yearsSubjectsGrades" se utiliza para almacenar todas las notas de las
// // asignaturas de todos los años
// // <Year, <Subject, Grade Value>>
// private Map<Integer, Map<String, Integer>> yearsSubjectsGrades;
//
// public StudentGradeDTO() {
// super(); // TODO Auto-generated constructor stub
// }
//
// public StudentGradeDTO(Integer id, String studentName) {
// super();
// this.id = id;
// this.studentName = studentName;
// }
//
// public StudentGradeDTO(Integer id, String studentName, Map<String, Integer>
// subjectsGrades,
// Map<Integer, Map<String, Integer>> yearsSubjectsGrades) {
// super();
// this.id = id;
// this.studentName = studentName;
// this.subjectsGrades = subjectsGrades;
// this.yearsSubjectsGrades = yearsSubjectsGrades;
// }
//
// public Integer getId() {
// return id;
// }
//
// public void setId(Integer id) {
// this.id = id;
// }
//
// public String getStudentName() {
// return studentName;
// }
//
// public void setStudentName(String studentName) {
// this.studentName = studentName;
// }
//
// public Map<String, Integer> getSubjectsGrades() {
// return subjectsGrades;
// }
//
// public void addSubjectsGrades(String subject, Integer gradeValue) {
// subjectsGrades.put(subject, gradeValue);
// }
//
// public Map<Integer, Map<String, Integer>> getYearsSubjectsGrades() {
// return yearsSubjectsGrades;
// }
//
// public void addYearsSubjectsGrades(Integer year, Map<String, Integer>
// subjectsGrades) {
// yearsSubjectsGrades.put(year, subjectsGrades);
// }
// }
