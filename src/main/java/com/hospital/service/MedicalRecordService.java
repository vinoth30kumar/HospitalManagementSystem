package com.hospital.service;

import java.util.List;

import com.hospital.payload.MedicalRecordDto;

public interface MedicalRecordService {
	
	public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecord, int patientId);
	
	public List<MedicalRecordDto>getAllMedicalRecord();
	
	public MedicalRecordDto getMedicalRecordById(int medicalId);
	
	public MedicalRecordDto updateMedicalRecordById(MedicalRecordDto medicalRecord, int medicalId);
	
	public void deleteMedicalRecordById(int medicalId);
	
	public List <MedicalRecordDto>getAllMedicalRecordByPatient(int patientId);
	
	

}
