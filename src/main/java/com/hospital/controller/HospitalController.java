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
import com.hospital.payload.MedicalRecordDto;
import com.hospital.service.MedicalRecordService;


@RestController
@RequestMapping("/api")
public class HospitalController {

	@Autowired
	private MedicalRecordService medicalRecordService;

	// To create medical record by parent patient Id
	@PostMapping("/patient/{patientId}/medical")
	public ResponseEntity<MedicalRecordDto> createmedicalRecord(@Valid @PathVariable int patientId,
			@RequestBody MedicalRecordDto medicalRecordDto) {

		MedicalRecordDto createdrecord = this.medicalRecordService.createMedicalRecord(medicalRecordDto, patientId);

		return new ResponseEntity<MedicalRecordDto>(createdrecord, HttpStatus.CREATED);

	}

	// To fetch medical record by Id
	@GetMapping("/medical/{medicalId}")
	public ResponseEntity<MedicalRecordDto> getmedicalRecordById(@PathVariable int medicalId) {
		MedicalRecordDto getmedicalRecord = this.medicalRecordService.getMedicalRecordById(medicalId);
		return new ResponseEntity<MedicalRecordDto>(getmedicalRecord, HttpStatus.OK);
	}

	// To fetch all medical record
	@GetMapping("/medical")
	public ResponseEntity<List<MedicalRecordDto>> getAllmedicalRecord() {
		List<MedicalRecordDto> getAllMedicalRecord = this.medicalRecordService.getAllMedicalRecord();

		return new ResponseEntity<List<MedicalRecordDto>>(getAllMedicalRecord, HttpStatus.OK);

	}

	// To update medical record by Id
	@PutMapping("/medical/{medicalId}")
	public ResponseEntity<MedicalRecordDto> updateMedicalRecordById(@RequestBody MedicalRecordDto medicalRecordDto,
			@PathVariable int medicalId) {

		MedicalRecordDto updatedMedicalRecord = this.medicalRecordService.updateMedicalRecordById(medicalRecordDto,
				medicalId);

		return new ResponseEntity<MedicalRecordDto>(updatedMedicalRecord, HttpStatus.OK);

	}

	// To delete medical record by Id
	@DeleteMapping("/medical/{medicalId}")
	public ResponseEntity<ApiResponse> deleteMedicalRecordById(@PathVariable int medicalId) {

		this.medicalRecordService.deleteMedicalRecordById(medicalId);

		ApiResponse response = new ApiResponse("MedicalRecord is deleted successfully", true);

		return new ResponseEntity<ApiResponse>(response, HttpStatus.OK);

	}

	// To fetch medical record by parent patient Id
	@GetMapping("/medicals/{patientId}")
	public ResponseEntity<List<MedicalRecordDto>> getAllMedicalRecordByPatient(@PathVariable int patientId) {

		List<MedicalRecordDto> allMedicalRecordByPatient = this.medicalRecordService
				.getAllMedicalRecordByPatient(patientId);

		return new ResponseEntity<List<MedicalRecordDto>>(allMedicalRecordByPatient, HttpStatus.OK);
	}

}
