package com.hospital.payload;

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
public class MedicalRecordDto {

	private int medicalId;

	private String dateOfAdmission;

	private String medicalPatientSummary;

	private PatientDto patient;

}
