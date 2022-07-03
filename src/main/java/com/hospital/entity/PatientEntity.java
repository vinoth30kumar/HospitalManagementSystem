package com.hospital.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "PatientRecord")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class PatientEntity {

	@Id
	//@SequenceGenerator(name="patient",initialValue=301,sequenceName = "patient")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;

	@NotEmpty
	@Size(min = 4, max = 20, message = "patient name can not be empty, contains minimum 4 character and maximum 20")
	@Column(nullable = false)
	private String patientName;

	@NotEmpty
	@Size(message = "patient age can not be empty")
	@Column(nullable = false)
	private String patientAge;

	@NotEmpty
	@Size(min = 4, max = 30, message = "patient diagnosis can not be empty, contains minimum 4 character and maximum 30")
	@Column(nullable = false)
	private String patientDiagnosis;

	@NotEmpty
	@Size(min = 4, max = 60, message = "patient address can not be empty, contains minimum 4 character and maximum 60")
	@Column(nullable = false)
	private String patientAddress;

	@ManyToOne
	private DoctorEntity doctor;

	@OneToMany(cascade = CascadeType.ALL)
	private List<MedicalRecordEntity> medicalRecordEntity = new ArrayList<MedicalRecordEntity>();

	
}
