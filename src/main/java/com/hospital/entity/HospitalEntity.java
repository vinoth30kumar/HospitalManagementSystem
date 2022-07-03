package com.hospital.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.SequenceGenerator;
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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "HospitalRecord")
public class HospitalEntity {

	@Id
	//@SequenceGenerator(name="hospital",initialValue=101,sequenceName = "hospital")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int hospitalId;

	@Column(nullable = false)
	@NotEmpty
	@Size(min = 4, max = 20, message = "hospital name can not be empty, contains minimum 4 character and maximum 20")
	private String hospitalName;

	@Column(nullable = false)
	@NotEmpty
	@Size(min = 4, max = 20, message = "Hospital Address can not be empty, contains minimum 10 character and maximum 60")
	private String hospitalAddress;

	@Column(nullable = false)
	@NotEmpty
	private String hospitalContact;

	@OneToMany(cascade = CascadeType.ALL)
	private List<DoctorEntity> doctorEntity = new ArrayList<DoctorEntity>();

	public int getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(int hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalAddress() {
		return hospitalAddress;
	}

	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}

	public String getHospitalContact() {
		return hospitalContact;
	}

	public void setHospitalContact(String hospitalContact) {
		this.hospitalContact = hospitalContact;
	}

	public List<DoctorEntity> getDoctorEntity() {
		return doctorEntity;
	}

	public void setDoctorEntity(List<DoctorEntity> doctorEntity) {
		this.doctorEntity = doctorEntity;
	}

	
}
