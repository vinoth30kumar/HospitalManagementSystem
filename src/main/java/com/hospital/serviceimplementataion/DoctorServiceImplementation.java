package com.hospital.serviceimplementataion;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.DoctorEntity;
import com.hospital.entity.HospitalEntity;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.payload.DoctorDto;
import com.hospital.payload.HospitalDto;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.HospitalRepository;
import com.hospital.service.DoctorService;



@Service
public class DoctorServiceImplementation implements DoctorService {

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	HospitalRepository hospitalRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public DoctorDto createDoctor(DoctorDto doctorDto, int hospitalId) {
		// TODO Auto-generated method stub

		HospitalEntity hospitalEntity = this.hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", "HospitalId", hospitalId));

		doctorDto.setHospital(this.modelMapper.map(hospitalEntity, HospitalDto.class));

		DoctorEntity doctorEntity = this.doctorDtoToDoctorEntity(doctorDto);

		DoctorEntity createdEntity = this.doctorRepository.save(doctorEntity);

		return this.doctorEntityToDoctorDto(createdEntity);
	}

	@Override
	public List<DoctorDto> getAllDoctor() {
		// TODO Auto-generated method stub
		List<DoctorDto> getAllDoctor = this.doctorRepository.findAll().stream()
				.map(doctor -> this.doctorEntityToDoctorDto(doctor)).collect(Collectors.toList());
		return getAllDoctor;
	}

	@Override
	public DoctorDto getDoctorById(int doctorId) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity = this.doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "DoctorId", doctorId));

		return this.doctorEntityToDoctorDto(doctorEntity);
	}

	@Override
	public DoctorDto updateDoctorById(DoctorDto doctorDto, int doctorId) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity = this.doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "DoctorId", doctorId));

		doctorEntity.setDoctorId(doctorId);
		doctorEntity.setDoctorName(doctorDto.getDoctorName());
		doctorEntity.setDesignation(doctorDto.getDesignation());
		doctorEntity.setDoctorSalary(doctorDto.getDoctorSalary());

		DoctorEntity saveEntity = this.doctorRepository.save(doctorEntity);

		return this.doctorEntityToDoctorDto(saveEntity);
	}

	@Override
	public void deleteDoctorById(int doctorId) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity = this.doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "DoctorId", doctorId));
		this.doctorRepository.delete(doctorEntity);

	}

	@Override
	public List<DoctorDto> getAllDoctorByHospital(int hospitalId) {
		// TODO Auto-generated method stub
		HospitalEntity hospitalEntity = this.hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", "HospitalId", hospitalId));

		List<DoctorEntity> doctorEntity = this.doctorRepository.findByHospital(hospitalEntity);

		List<DoctorDto> doctorDto = doctorEntity.stream().map(doctors -> this.modelMapper.map(doctors, DoctorDto.class))
				.collect(Collectors.toList());
		return doctorDto;
	}

	public DoctorEntity doctorDtoToDoctorEntity(DoctorDto doctorDto) {
		// TODO Auto-generated method stub
		return this.modelMapper.map(doctorDto, DoctorEntity.class);
	}

	public DoctorDto doctorEntityToDoctorDto(DoctorEntity doctorEntity) {
		return this.modelMapper.map(doctorEntity, DoctorDto.class);

	}

}
