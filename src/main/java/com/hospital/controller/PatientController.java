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
import com.hospital.payload.PatientDto;
import com.hospital.service.PatientService;


@RestController
@RequestMapping("/api")
public class PatientController {
	@Autowired
	private PatientService patientService;

	// To create a patient by parent doctor Id
	@PostMapping("/doctor/{doctorId}/patient")
	public ResponseEntity<PatientDto> createPatient(@Valid @PathVariable int doctorId, @RequestBody PatientDto patientDto) {

		PatientDto createdPatient = this.patientService.createPatient(patientDto, doctorId);

		return new ResponseEntity<PatientDto>(createdPatient, HttpStatus.CREATED);
	}

	// To fetch patient by Id
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<PatientDto> getPatientById(@PathVariable int patientId) {

		PatientDto getPatient = this.patientService.getPatientById(patientId);

		return new ResponseEntity<PatientDto>(getPatient, HttpStatus.OK);

	}

	// To fetch all patient
	@GetMapping("/patient")
	public ResponseEntity<List<PatientDto>> getAllPatients() {

		List<PatientDto> getAllPatients = this.patientService.getAllPatient();

		return new ResponseEntity<List<PatientDto>>(getAllPatients, HttpStatus.OK);

	}

	// To update patient by Id
	@PutMapping("/patient/{patientId}")
	public ResponseEntity<PatientDto> updatePatientById(@RequestBody PatientDto patient, @PathVariable int patientId) {

		PatientDto updatedPatient = this.patientService.updatePatientById(patient, patientId);

		return new ResponseEntity<PatientDto>(updatedPatient, HttpStatus.OK);
	}

	// To delete patient By Id
	@DeleteMapping("/patient/{patientId}")
	public ResponseEntity<ApiResponse> deletePatientById(@PathVariable int patientId) {

		this.patientService.deletePatientById(patientId);
		ApiResponse response = new ApiResponse("Patient Record is deleted successfully", true);
		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);
	}

	// To fetch patient by parent doctorId
	@GetMapping("/patients/{doctorId}")
	public ResponseEntity<List<PatientDto>> getAllpatientByDoctor(@PathVariable int doctorId) {

		List<PatientDto> allPatientByDoctor = this.patientService.getAllPatientDetailsByDoctor(doctorId);
		return new ResponseEntity<List<PatientDto>>(allPatientByDoctor, HttpStatus.OK);

	}
}
