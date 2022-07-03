package com.hospital.service;

import java.util.List;

import com.hospital.payload.DoctorDto;

public interface DoctorService {
	
	public DoctorDto createDoctor(DoctorDto doctor, int hospitalId);
	
	public List<DoctorDto> getAllDoctor();
	
	public DoctorDto updateDoctorById(DoctorDto doctor, int doctorId);
	
	public void deleteDoctorById(int doctorId);
	
	public List<DoctorDto> getAllDoctorByHospital(int hospitalId);

	public DoctorDto getDoctorById(int doctorId);

}
