package com.hospital.service;

import java.util.List;

import com.hospital.payload.PatientDto;



public interface PatientService {
	
	public PatientDto createPatient(PatientDto patient, int doctorId);
	
	public List<PatientDto> getAllPatient();
	
	public PatientDto getPatientById(int patientId);
	
	public PatientDto updatePatientById(PatientDto patientDto, int patientId);
	
	public void deletePatientById(int patientId);
	
	public List<PatientDto> getAllPatientDetailsByDoctor(int doctorId);

}

