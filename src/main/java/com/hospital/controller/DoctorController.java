package com.hospital.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.exception.ApiResponse;
import com.hospital.payload.DoctorDto;
import com.hospital.service.DoctorService;




@RestController
@RequestMapping("/api")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	// To create doctor
	@PostMapping("/hospital/{hospitalId}/doctor")
	public ResponseEntity<DoctorDto> createDoctor(@Valid @PathVariable int hospitalId, @RequestBody DoctorDto doctorDto) {

		DoctorDto createdDoctor = this.doctorService.createDoctor(doctorDto, hospitalId);

		return new ResponseEntity<DoctorDto>(createdDoctor, HttpStatus.CREATED);
	}

	// To fetch doctor by Id
	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<DoctorDto> getDoctorById(@PathVariable int doctorId) {

		DoctorDto getDoctor = this.doctorService.getDoctorById(doctorId);
		System.out.println("Doctor id is :" + doctorId);

		return new ResponseEntity<DoctorDto>(getDoctor, HttpStatus.OK);

	}

	// To fetch all doctor
	@GetMapping("/doctor")
	public ResponseEntity<List<DoctorDto>> getAllDoctors() {
		List<DoctorDto> getAllDoctors = this.doctorService.getAllDoctor();

		return new ResponseEntity<List<DoctorDto>>(getAllDoctors, HttpStatus.OK);
	}

	// To update doctor by Id
	@PutMapping("/doctor/{doctorId}")
	public ResponseEntity<DoctorDto> updateDoctorById(@RequestBody DoctorDto doctor, @PathVariable int doctorId) {

		DoctorDto updatedDoctor = this.doctorService.updateDoctorById(doctor, doctorId);
		return new ResponseEntity<DoctorDto>(updatedDoctor, HttpStatus.OK);

	}

	// To delete doctor by Id
	@DeleteMapping("/doctor/{doctorId}")
	public ResponseEntity<ApiResponse> deleteDoctorById(@PathVariable int doctorId) {
		this.doctorService.deleteDoctorById(doctorId);
		ApiResponse response = new ApiResponse("DoctorRecord is deleted successfully", true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	// To fetch doctor by hospital parent Id
	@GetMapping("/doctors/{hospitalId}")
	public ResponseEntity<List<DoctorDto>> getAlldoctorByHospital(@PathVariable int hospitalId) {
		List<DoctorDto> allDoctorByHospital = this.doctorService.getAllDoctorByHospital(hospitalId);
		return new ResponseEntity<List<DoctorDto>>(allDoctorByHospital, HttpStatus.OK);

	}

}
