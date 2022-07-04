package com.hospital;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import com.hospital.entity.DoctorEntity;
import com.hospital.entity.HospitalEntity;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.HospitalRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DoctorRepositoryTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Test
	@Order(1)
	public void saveDoctorTest() {
		DoctorEntity doctorEntity = DoctorEntity.builder().doctorName("Vignesh").designation("Cardiologist")
				.doctorSalary("500000").build();

		doctorRepository.save(doctorEntity);

		Assertions.assertThat(doctorEntity.getDoctorId()).isGreaterThan(0);

	}

	@Test
	@Order(2)
	public void getDoctorByIdTest() {
		DoctorEntity doctorEntity = doctorRepository.findById(1).get();

		Assertions.assertThat(doctorEntity.getDoctorId()).isEqualTo(1);

	}

	@Test
	@Order(3)
	public void getAllDoctorTest() {

		List<DoctorEntity> doctorEntity = this.doctorRepository.findAll();

		Assertions.assertThat(doctorEntity.size()).isGreaterThan(0);

	}

	@Test
	@Order(4)
	public void updateDoctorTest() {

		DoctorEntity doctorEntity = doctorRepository.findById(1).get();

		doctorEntity.setDesignation("Neurologist");
		DoctorEntity doctorEntity1 = doctorRepository.save(doctorEntity);

		Assertions.assertThat(doctorEntity1.getDesignation()).isEqualTo("Neurologist");
	}

	/*
	 * @Test
	 * 
	 * @Order(5) public void deleteDoctorTest() {
	 * 
	 * DoctorEntity doctorEntity = doctorRepository.findById(1).get();
	 * 
	 * doctorRepository.delete(doctorEntity);
	 * 
	 * DoctorEntity doctorEntity1 = null;
	 * 
	 * Optional<DoctorEntity> optionalDoctorEntity =
	 * doctorRepository.findBydoctorName("Archana");
	 * 
	 * if (optionalDoctorEntity.isPresent()) {
	 * 
	 * doctorEntity1 = optionalDoctorEntity.get();
	 * 
	 * }
	 * 
	 * Assertions.assertThat(doctorEntity1).isNull();
	 * 
	 * }
	 */

	@Test
	@Order(6)
	public void getDoctorByHospitalid() {

		HospitalEntity hospital = hospitalRepository.findById(1).get();

		List<DoctorEntity> doctorList = doctorRepository.findByHospital(hospital);

		Assertions.assertThat(doctorList.size()).isGreaterThan(0);

	}

}
