package com.hospital.serviceimplementataion;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.DoctorEntity;
import com.hospital.entity.PatientEntity;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.payload.DoctorDto;
import com.hospital.payload.PatientDto;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.service.PatientService;

@Service
public class PatientServiceImplementation implements PatientService {
	@Autowired
	PatientRepository patientRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PatientDto createPatient(PatientDto patient, int doctorId) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity = this.doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "DoctorId", doctorId));

		patient.setDoctor(this.modelMapper.map(doctorEntity, DoctorDto.class));

		PatientEntity patientEntity = this.patientDtoToPatientEntity(patient);

		PatientEntity createdEntity = this.patientRepository.save(patientEntity);

		return this.patientEntityToPatientDto(createdEntity);
	}

	@Override
	public List<PatientDto> getAllPatient() {
		// TODO Auto-generated method stub
		List<PatientDto> getAllPatient = this.patientRepository.findAll().stream()
				.map(patient -> this.patientEntityToPatientDto(patient)).collect(Collectors.toList());
		return getAllPatient;
	}

	@Override
	public PatientDto getPatientById(int patientId) {
		// TODO Auto-generated method stub
		PatientEntity patientEntity = this.patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "PatientId", patientId));

		return this.patientEntityToPatientDto(patientEntity);
	}

	@Override
	public PatientDto updatePatientById(PatientDto patientDto, int patientId) {
		// TODO Auto-generated method stub

		PatientEntity patientEntity = this.patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "PatientId", patientId));
patientEntity.setPatientId(patientId);
// 	    patientEntity.setPatientId(patientId);
//		patientEntity.setPatientName(patientDto.getPatientName());
//		patientEntity.setPatientAge(patientDto.getPatientAge());
//		patientEntity.setPatientDiagnosis(patientDto.getPatientDiagnosis());
//		patientEntity.setPatientAddress(patientDto.getPatientAddress());

		PatientEntity saveEntity = this.patientRepository.save(patientEntity);

		return this.patientEntityToPatientDto(saveEntity);
	}

	@Override
	public void deletePatientById(int patientId) {
		// TODO Auto-generated method stub

		PatientEntity patientEntity = this.patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "PatientId", patientId));

		this.patientRepository.delete(patientEntity);

	}

	@Override
	public List<PatientDto> getAllPatientDetailsByDoctor(int doctorId) {
		// TODO Auto-generated method stub

		DoctorEntity doctorEntity = this.doctorRepository.findById(doctorId)
				.orElseThrow(() -> new ResourceNotFoundException("Doctor", "DoctorId", doctorId));

		List<PatientEntity> patientEntity = this.patientRepository.findByDoctor(doctorEntity);

		List<PatientDto> patientDto = patientEntity.stream()
				.map(patients -> this.modelMapper.map(patients, PatientDto.class)).collect(Collectors.toList());
		return patientDto;
	}

	public PatientEntity patientDtoToPatientEntity(PatientDto patientDto) {

		return this.modelMapper.map(patientDto, PatientEntity.class);

	}

	public PatientDto patientEntityToPatientDto(PatientEntity patientEntity) {

		return this.modelMapper.map(patientEntity, PatientDto.class);

	}

}
