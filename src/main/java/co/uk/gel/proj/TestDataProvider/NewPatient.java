package co.uk.gel.proj.TestDataProvider;

import co.uk.gel.proj.util.TestUtils;

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

	private String orderingEntity;
	private String responsibleClinicianName;
	private String responsibleClinicianEmail;
	private String responsibleClinicianContactNumber;

	private String clinicalIndicationTestTypeSampleType;
	private int totalNumberOfUncheckedTumourList;


	public String getClinicalIndication() {
		return clinicalIndication;
	}

	public void setClinicalIndication(String clinicalIndication) {
		this.clinicalIndication = clinicalIndication;
	}

	private String clinicalIndication;
	
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
		this.title = title;
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

	public String getPatientDOBInMonthFormat(){
		return TestUtils.getDOBInMonthFormat(this.day +"-"+this.month+"-"+this.year);
	}

	public String getPatientFullName(){
		return this.lastName.toUpperCase()+", "+this.firstName;
	}

	public String getOrderingEntity() {
		return orderingEntity;
	}

	public void setOrderingEntity(String orderingEntity) {
		this.orderingEntity = orderingEntity;
	}

	public String getResponsibleClinicianName() {
		return responsibleClinicianName;
	}

	public void setResponsibleClinicianName(String responsibleClinicianName) {
		this.responsibleClinicianName = responsibleClinicianName;
	}

	public String getResponsibleClinicianEmail() {
		return responsibleClinicianEmail;
	}

	public void setResponsibleClinicianEmail(String responsibleClinicianEmail) {
		this.responsibleClinicianEmail = responsibleClinicianEmail;
	}

	public String getResponsibleClinicianContactNumber() {
		return responsibleClinicianContactNumber;
	}

	public void setResponsibleClinicianContactNumber(String responsibleClinicianContactNumber) {
		this.responsibleClinicianContactNumber = responsibleClinicianContactNumber;
	}

	public String getClinicalIndicationTestTypeSampleType() {
		return clinicalIndicationTestTypeSampleType;
	}

	public void setClinicalIndicationTestTypeSampleType(String clinicalIndicationTestTypeSampleType) {
		this.clinicalIndicationTestTypeSampleType = clinicalIndicationTestTypeSampleType;
	}

	public int getTotalNumberOfUncheckedTumourList() {
		return totalNumberOfUncheckedTumourList;
	}

	public void setTotalNumberOfUncheckedTumourList (int totalNumberOfUncheckedTumourList) {
		this.totalNumberOfUncheckedTumourList = totalNumberOfUncheckedTumourList;
	}
}
