package co.uk.gel.proj.TestDataProvider;

public class NewPatient {

	private int index;
	private String subHeaderText;
	private String bodyText;
	private String iconID;
	private String linkText;
	private String totalNumberOfResults;
	private String keyword;

	private String title;
	private String day;
	private String month;
	private String year;
	private String firstName;
	private String lastName;
	private String gender;
	private String postCode;
	private String nhsNumber;
	private String hospitalNumber;
	private int numberOfMandatoryFields;
	private String tumourDescription;
	private String tumourType;
	private String tumourSpecimenID;
	private String sampleType;
	private String sampleState;
	private String sampleID;
	
	private String referralID;
	private String referralHumanReadableID;
	private String patientID;
	private String patientHumanReadableID;
	
	public int getIndex() {
		return index;
	}

	public String getSubHeaderText() {
		return subHeaderText;
	}

	public String getBodyText() {
		return bodyText;
	}

	public String getIconID() {
		return iconID;
	}

	public String getLinkText() {
		return linkText;
	}

	public String getTotalNumberOfResults() {
		return totalNumberOfResults;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.firstName = title;
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
	
	public String getPostCode() {
		return postCode;
	}
	
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	
	public String getDay() {
		return day;
	}
	
	public void setDay(String day) {
		this.day = day;
	}
	
	public String getMonth() {
		return month;
	}
	
	public void setMonth(String month) {
		this.month = month;
	}
	
	public String getYear() {
		return year;
	}
	
	public void setYear(String year) {
		this.year = year;
	}

	public String getTumourSpecimenID() {
		return tumourSpecimenID;
	}

	public void setTumourSpecimenID(String tumourSpecimenID) {
		this.tumourSpecimenID = tumourSpecimenID;
	}

	public String getNhsNumber() {
		return nhsNumber;
	}

	public void setNhsNumber(String nhsNumber) {
		this.nhsNumber = nhsNumber;
	}

	public int getNumberOfMandatoryFields() {
		return numberOfMandatoryFields;
	}

	public void setNumberOfMandatoryFields(int numberOfMandatoryFields) {
		this.numberOfMandatoryFields = numberOfMandatoryFields;
	}

	public String getTumourDescription() {
		return tumourDescription;
	}

	public void setTumourDescription(String tumourDescription) {
		this.tumourDescription = tumourDescription;
	}

	public String getTumourType() {
		return tumourType;
	}

	public void setTumourType(String tumourType) {
		this.tumourType = tumourType;
	}

	public String getSampleType() {
		return sampleType;
	}

	public void setSampleType(String sampleType) {
		this.sampleType = sampleType;
	}

	public String getSampleState() {
		return sampleState;
	}

	public void setSampleState(String sampleState) {
		this.sampleState = sampleState;
	}

	public String getSampleID() {
		return sampleID;
	}

	public void setSampleID(String sampleID) {
		this.sampleID = sampleID;
	}

	public String getReferralID() {
		return referralID;
	}

	public void setReferralID(String referralID) {
		this.referralID = referralID;
	}

	public String getHospitalNumber() {
		return hospitalNumber;
	}

	public void setHospitalNumber(String hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}

	public String getReferralHumanReadableID() {
		return referralHumanReadableID;
	}

	public void setReferralHumanReadableID(String referralHumanReadableID) {
		this.referralHumanReadableID = referralHumanReadableID;
	}

	public String getPatientID() {
		return patientID;
	}

	public void setPatientID(String patientID) {
		this.patientID = patientID;
	}

	public String getPatientHumanReadableID() {
		return patientHumanReadableID;
	}

	public void setPatientHumanReadableID(String patientHumanReadableID) {
		this.patientHumanReadableID = patientHumanReadableID;
	}
}
