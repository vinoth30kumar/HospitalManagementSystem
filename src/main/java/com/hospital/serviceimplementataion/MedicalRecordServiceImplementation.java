package com.hospital.serviceimplementataion;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.MedicalRecordEntity;
import com.hospital.entity.PatientEntity;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.payload.MedicalRecordDto;
import com.hospital.payload.PatientDto;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.PatientRepository;
import com.hospital.service.MedicalRecordService;

@Service
public class MedicalRecordServiceImplementation implements MedicalRecordService {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	PatientRepository patientRepository;

	@Override
	public MedicalRecordDto createMedicalRecord(MedicalRecordDto medicalRecord, int patientId) {
		// TODO Auto-generated method stub

		PatientEntity patientEntity = this.patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "PatientId", patientId));

		medicalRecord.setPatient(this.modelMapper.map(patientEntity, PatientDto.class));
		MedicalRecordEntity medicalRecordEntity = this.medicalRecordDtoToMedicalRecordEntity(medicalRecord);

		MedicalRecordEntity createdEntity = this.medicalRecordRepository.save(medicalRecordEntity);

		return this.medicalRecordEntityToMedicalRecordDto(createdEntity);

	}

	@Override
	public List<MedicalRecordDto> getAllMedicalRecord() {
		// TODO Auto-generated method stub

		List<MedicalRecordDto> getAllMedicalRecord = this.medicalRecordRepository.findAll().stream()
				.map(patient -> this.medicalRecordEntityToMedicalRecordDto(patient)).collect(Collectors.toList());

		return getAllMedicalRecord;
	}

	@Override
	public MedicalRecordDto getMedicalRecordById(int medicalId) {
		// TODO Auto-generated method stub
		MedicalRecordEntity medicalRecordEntity = this.medicalRecordRepository.findById(medicalId)
				.orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "MedicalId", medicalId));

		return this.medicalRecordEntityToMedicalRecordDto(medicalRecordEntity);
	}

	@Override
	public MedicalRecordDto updateMedicalRecordById(MedicalRecordDto medicalRecord, int medicalId) {
		// TODO Auto-generated method stub
		MedicalRecordEntity medicalRecordEntity = this.medicalRecordRepository.findById(medicalId)
				.orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "MedicalId", medicalId));
		medicalRecordEntity.setMedicalId(medicalId);
		medicalRecordEntity.setDateOfAdmission(medicalRecord.getDateOfAdmission());
		medicalRecordEntity.setMedicalPatientSummary(medicalRecord.getMedicalPatientSummary());

		MedicalRecordEntity saveEntity = this.medicalRecordRepository.save(medicalRecordEntity);

		return this.medicalRecordEntityToMedicalRecordDto(saveEntity);
	}

	@Override
	public void deleteMedicalRecordById(int medicalId) {
		// TODO Auto-generated method stub
		MedicalRecordEntity medicalRecordEntity = this.medicalRecordRepository.findById(medicalId)
				.orElseThrow(() -> new ResourceNotFoundException("MedicalRecord", "MedicalId", medicalId));

		this.medicalRecordRepository.delete(medicalRecordEntity);

	}

	@Override
	public List<MedicalRecordDto> getAllMedicalRecordByPatient(int patientId) {
		// TODO Auto-generated method stub

		PatientEntity patientEntity = this.patientRepository.findById(patientId)
				.orElseThrow(() -> new ResourceNotFoundException("Patient", "PatientId", patientId));

		List<MedicalRecordEntity> medicalRecordEntity = this.medicalRecordRepository.findByPatient(patientEntity);

		List<MedicalRecordDto> medicalRecordDto = medicalRecordEntity.stream()
				.map(medical -> this.modelMapper.map(medical, MedicalRecordDto.class)).collect(Collectors.toList());

		return medicalRecordDto;
	}

	public MedicalRecordEntity medicalRecordDtoToMedicalRecordEntity(MedicalRecordDto medicalRecordDto) {

		return this.modelMapper.map(medicalRecordDto, MedicalRecordEntity.class);

	}

	public MedicalRecordDto medicalRecordEntityToMedicalRecordDto(MedicalRecordEntity medicalRecordEntity) {

		return this.modelMapper.map(medicalRecordEntity, MedicalRecordDto.class);

	}

}
