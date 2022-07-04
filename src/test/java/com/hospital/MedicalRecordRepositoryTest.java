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

import com.hospital.entity.MedicalRecordEntity;
import com.hospital.entity.PatientEntity;
import com.hospital.repository.MedicalRecordRepository;
import com.hospital.repository.PatientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

public class MedicalRecordRepositoryTest {

	@BeforeEach
	void setUp() throws Exception {

	}

	@Autowired
	private MedicalRecordRepository medicalRecordRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Test
	@Order(1)
	public void saveMedicalRecordTest() {

		MedicalRecordEntity medicalRecord = MedicalRecordEntity.builder().dateOfAdmission("21/03.2021")
				.medicalPatientSummary("Cleared all test. Patient is in good Health").build();

		medicalRecordRepository.save(medicalRecord);

		Assertions.assertThat(medicalRecord.getMedicalId()).isGreaterThan(0);

	}

	@Test
	@Order(2)
	public void getMedicalRecordByIdTest() {

		MedicalRecordEntity medicalRecord = medicalRecordRepository.findById(2).get();

		Assertions.assertThat(medicalRecord.getMedicalId()).isEqualTo(2);

	}

	@Test
	@Order(3)
	public void getAllMedicalRecord() {

		List<MedicalRecordEntity> medicalRecord = this.medicalRecordRepository.findAll();

		Assertions.assertThat(medicalRecord.size()).isGreaterThan(0);

	}

	@Test
	@Order(4)
	public void updateMedicalRecordTest() {

		MedicalRecordEntity medicalRecord = medicalRecordRepository.findById(2).get();

		medicalRecord.setDateOfAdmission("25/06/2021");

		MedicalRecordEntity medicalRecord1 = medicalRecordRepository.save(medicalRecord);

		Assertions.assertThat(medicalRecord1.getDateOfAdmission()).isEqualTo("25/06/2021");

	}

	@Test
	@Order(5)
	public void deleteMedicalRecord() {

		MedicalRecordEntity medicalRecord = medicalRecordRepository.findById(2).get();

		medicalRecordRepository.delete(medicalRecord);

		MedicalRecordEntity medicalRecord1 = null;

		Optional<MedicalRecordEntity> optionalMedicalRecord = medicalRecordRepository
				.findByDateOfAdmission(" 23/02/2020");

		if (optionalMedicalRecord.isPresent()) {

			medicalRecord1 = optionalMedicalRecord.get();

		}

		Assertions.assertThat(medicalRecord1).isNull();

	}

	@Test
	@Order(6)
	public void getMedicalRecordByPatient() {

		PatientEntity patientEntity = patientRepository.findById(1).get();

		List<MedicalRecordEntity> medicalRecordList = medicalRecordRepository.findByPatient(patientEntity);

		Assertions.assertThat(medicalRecordList.size()).isGreaterThan(0);

	}

}
Footer

