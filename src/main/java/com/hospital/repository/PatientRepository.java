package com.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.DoctorEntity;
import com.hospital.entity.PatientEntity;


public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

	List<PatientEntity> findByDoctor(DoctorEntity doctor);

	Optional<PatientEntity> findByPatientName(String patientName);

}
