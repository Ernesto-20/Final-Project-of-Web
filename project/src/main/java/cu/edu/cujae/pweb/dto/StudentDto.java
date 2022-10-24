package cu.edu.cujae.pweb.dto;

public class StudentDto {

	private String id;
	private String idNum;
	private String firstName;
	private String lastName;
	private String gender;
	private String municipality;
	private String statusID;
	private boolean newRecord;
	
	public StudentDto() {
		super(); // TODO Auto-generated constructor stub
	}
	
	public StudentDto(String id, String idNum, String firstName, String lastName, String gender, String municipality,
			String statusID, boolean newRecord) {
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
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getStatusID() {
		return statusID;
	}
	public void setStatusID(String statusID) {
		this.statusID = statusID;
	}
	public boolean isNewRecord() {
		return newRecord;
	}
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}
}
