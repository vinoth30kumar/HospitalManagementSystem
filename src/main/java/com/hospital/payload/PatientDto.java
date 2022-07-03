package com.hospital.payload;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientDto {

	private int patientId;

	private String patientName;

	private String patientAge;

	private String patientDiagnosis;

	private String patientAddress;

	private DoctorDto doctor;

	// private List<MedicalRecordDto> medicalRecordDto;

}
