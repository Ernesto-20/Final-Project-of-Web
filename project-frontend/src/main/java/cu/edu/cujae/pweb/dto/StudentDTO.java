package cu.edu.cujae.pweb.dto;

public class StudentDTO {

	private Integer id;
	private String idNum;
	private String firstName;
	private String lastName;
	private String gender;
	private String municipality;
	private Integer statusID;
	private String statusDescription;
	private String fullName;

	public StudentDTO(Integer id, String idNum, String firstName, String lastName, String gender, String municipality, int statusId, String statusDescription) {
		this.id = id;
		this.idNum = idNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.municipality = municipality;
		this.statusID = statusId;
		this.statusDescription = statusDescription;
		this.fullName = firstName + " " + lastName;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public StudentDTO() {
		super(); // TODO Auto-generated constructor stub
	}

	public StudentDTO(Integer id, String idNum, String firstName, String lastName, String gender, String municipality,
			Integer statusID) {
		super();
		this.id = id;
		this.idNum = idNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.municipality = municipality;
		this.statusID = statusID;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}

