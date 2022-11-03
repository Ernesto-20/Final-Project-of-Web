package cu.edu.cujae.pweb.dto;

public class StudentDTO {

	private Integer id;
	private String idNum;
	private String firstName;
	private String lastName;
	private String gender;
	private String municipality;
	private Integer statusID;
	private boolean newRecord;
	
	public StudentDTO() {
		super(); // TODO Auto-generated constructor stub
	}
	
	public StudentDTO(Integer id, String idNum, String firstName, String lastName, String gender, String municipality,
			Integer statusID, boolean newRecord) {
		super();
		this.id = id;
		this.idNum = idNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.municipality = municipality;
		this.statusID = statusID;
		this.newRecord = newRecord;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
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
	public Integer getStatusID() {
		return statusID;
	}
	public void setStatusID(Integer statusID) {
		this.statusID = statusID;
	}
	public boolean isNewRecord() {
		return newRecord;
	}
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
