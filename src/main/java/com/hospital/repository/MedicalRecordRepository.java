package com.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.MedicalRecordEntity;
import com.hospital.entity.PatientEntity;


public interface MedicalRecordRepository extends JpaRepository<MedicalRecordEntity, Integer> {

	List<MedicalRecordEntity> findByPatient(PatientEntity patient);

	Optional<MedicalRecordEntity> findByDateOfAdmission(String dateOfAdmission);
}

