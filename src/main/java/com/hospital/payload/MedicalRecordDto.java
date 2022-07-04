package com.hospital.payload;


public class MedicalRecordDto {

	private int medicalId;

	private String dateOfAdmission;

	private String medicalPatientSummary;

	private PatientDto patient;

	public int getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(int medicalId) {
		this.medicalId = medicalId;
	}

	public String getDateOfAdmission() {
		return dateOfAdmission;
	}

	public void setDateOfAdmission(String dateOfAdmission) {
		this.dateOfAdmission = dateOfAdmission;
	}

	public String getMedicalPatientSummary() {
		return medicalPatientSummary;
	}

	public void setMedicalPatientSummary(String medicalPatientSummary) {
		this.medicalPatientSummary = medicalPatientSummary;
	}

	public PatientDto getPatient() {
		return patient;
	}

	public void setPatient(PatientDto patient) {
		this.patient = patient;
	}

	public MedicalRecordDto(int medicalId, String dateOfAdmission, String medicalPatientSummary, PatientDto patient) {
		super();
		this.medicalId = medicalId;
		this.dateOfAdmission = dateOfAdmission;
		this.medicalPatientSummary = medicalPatientSummary;
		this.patient = patient;
	}

	public MedicalRecordDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "MedicalRecordDto [medicalId=" + medicalId + ", dateOfAdmission=" + dateOfAdmission
				+ ", medicalPatientSummary=" + medicalPatientSummary + ", patient=" + patient + "]";
	}
	
	

}
