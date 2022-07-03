package com.hospital.service;

import java.util.List;

import com.hospital.payload.HospitalDto;

public interface HospitalService {

	public HospitalDto createHospital(HospitalDto hospital);
	
	public HospitalDto getHospitalById(int hospitalId);
	
	public HospitalDto updateHospitalById(HospitalDto hospitalDto,int hospitalId);
	
	public void deleteHospitalById(int hospitalId);
	
	public List<HospitalDto> getAllHospital();
	
}
