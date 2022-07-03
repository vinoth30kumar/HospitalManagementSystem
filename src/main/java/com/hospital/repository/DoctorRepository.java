package com.hospital.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hospital.entity.DoctorEntity;
import com.hospital.entity.HospitalEntity;


public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {

	List<DoctorEntity> findByHospital(HospitalEntity hospital);

	Optional<DoctorEntity> findBydoctorName(String doctorName);

}