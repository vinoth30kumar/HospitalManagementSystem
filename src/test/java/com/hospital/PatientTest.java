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
import com.hospital.entity.PatientEntity;
import com.hospital.repository.DoctorRepository;
import com.hospital.repository.PatientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatientTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Test
	@Order(1)
	public void savePatientTest() {

		PatientEntity patientEntity = PatientEntity.builder().patientName("Magesh").patientAge("50")
				.patientDiagnosis("Heart Attack ").patientAddress("Tirunelveli").build();

		patientRepository.save(patientEntity);

		Assertions.assertThat(patientEntity.getPatientId()).isGreaterThan(0);

	}

	@Test
	@Order(2)

	public void getPatientById() {
		PatientEntity patientEntity = patientRepository.findById(1).get();
		Assertions.assertThat(patientEntity.getPatientId()).isEqualTo(1);

	}

	@Test
	@Order(3)
	public void getAllPatientTest() {

		List<PatientEntity> patientEntity = this.patientRepository.findAll();
		Assertions.assertThat(patientEntity.size()).isGreaterThan(0);

	}

	@Test
	@Order(4)
	public void updatePatientById() {
		PatientEntity patientEntity = patientRepository.findById(1).get();

		patientEntity.setPatientName("Peter");

		PatientEntity patientEntity1 = patientRepository.save(patientEntity);

		Assertions.assertThat(patientEntity1.getPatientName()).isEqualTo("Peter");

	}

	/*
	 * @Test
	 * 
	 * @Order(5) public void deletePatientTest() { PatientEntity patientEntity =
	 * patientRepository.findById(1).get();
	 * 
	 * patientRepository.delete(patientEntity);
	 * 
	 * PatientEntity patientEntity1 = null;
	 * 
	 * Optional<PatientEntity> optionalPatientEntity =
	 * patientRepository.findByPatientName("Swathi");
	 * 
	 * if (optionalPatientEntity.isPresent()) {
	 * 
	 * patientEntity1 = optionalPatientEntity.get();
	 * 
	 * }
	 * 
	 * Assertions.assertThat(patientEntity1).isNull();
	 * 
	 * }
	 */
	@Test
	@Order(6)
	public void getPatientByDoctor() {

		DoctorEntity doctorEntity = doctorRepository.findById(1).get();

		List<PatientEntity> patientList = patientRepository.findByDoctor(doctorEntity);

		Assertions.assertThat(patientList.size()).isGreaterThan(0);

	}

}