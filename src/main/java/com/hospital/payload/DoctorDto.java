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
public class DoctorDto {

	private int doctorid;

	private String doctorName;

	private String designation;

	private String doctorSalary;

	private HospitalDto hospital;

	public int getDoctorid() {
		return doctorid;
	}

	public void setDoctorid(int doctorid) {
		this.doctorid = doctorid;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDoctorSalary() {
		return doctorSalary;
	}

	public void setDoctorSalary(String doctorSalary) {
		this.doctorSalary = doctorSalary;
	}

	public HospitalDto getHospital() {
		return hospital;
	}

	public void setHospital(HospitalDto map) {
		// TODO Auto-generated method stub
		
	}

		
	
		// private List<PatientDto> patientDto;

}