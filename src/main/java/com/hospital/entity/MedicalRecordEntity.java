package com.hospital.entity;

//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MedicalRecord")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordEntity {

	@Id
	//@SequenceGenerator(name="medical",initialValue=401,sequenceName = "medical")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int medicalId;

	@NotEmpty
	@Size( message ="date of admission can not be empty")
	@Column(nullable = false)
	private String dateOfAdmission;

	@NotEmpty
	@Size(min = 4, max = 50, message = "medical patient summary can not be empty, contains minimum 4 character and maximum 50")
	@Column(nullable = false)
	private String medicalPatientSummary;

	@ManyToOne
	private PatientEntity patient;

	
	
	
	}

	

	


