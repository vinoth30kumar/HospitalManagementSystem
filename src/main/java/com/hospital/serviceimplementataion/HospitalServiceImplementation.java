package com.hospital.serviceimplementataion;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hospital.entity.HospitalEntity;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.payload.HospitalDto;
import com.hospital.repository.HospitalRepository;
import com.hospital.service.HospitalService;



@Service
public class HospitalServiceImplementation implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public HospitalDto createHospital(HospitalDto hospital) {
		// TODO Auto-generated method stub

		HospitalEntity hospitalEntity = this.hospitalDtoToHospitalEntity(hospital);
		HospitalEntity savedHospital = this.hospitalRepository.save(hospitalEntity);
		return this.hospitalEntityToHospitalDto(savedHospital);
	}

	@Override
	public HospitalDto getHospitalById(int hospitalId) {

		// TODO Auto-generated method stub
		HospitalEntity hospitalEntity = this.hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", "HospitalId", hospitalId));

		return this.hospitalEntityToHospitalDto(hospitalEntity);
	}

	@Override
	public HospitalDto updateHospitalById(HospitalDto hospitalDto, int hospitalId) {

		// TODO Auto-generated method stub

		HospitalEntity hospitalEntity = this.hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", "HospitalId", hospitalId));

		hospitalEntity.setHospitalId(hospitalDto.getHospitalId());
		hospitalEntity.setHospitalName(hospitalDto.getHospitalName());
		hospitalEntity.setHospitalAddress(hospitalDto.getHospitalAddress());
		hospitalEntity.setHospitalContact(hospitalDto.getHospitalContact());
		HospitalEntity savedEntity = this.hospitalRepository.save(hospitalEntity);
		return this.hospitalEntityToHospitalDto(hospitalEntity);
	}

	@Override
	public void deleteHospitalById(int hospitalId) {
		// TODO Auto-generated method stub
		HospitalEntity hospitalEntity = this.hospitalRepository.findById(hospitalId)
				.orElseThrow(() -> new ResourceNotFoundException("Hospital", "HospitalId", hospitalId));

		this.hospitalRepository.delete(hospitalEntity);
	}

	@Override
	public List<HospitalDto> getAllHospital() {
		// TODO Auto-generated method stub

		List<HospitalEntity> hospitalList = this.hospitalRepository.findAll();
		List<HospitalDto> hospitalDtoList = hospitalList.stream()
				.map(hospital1 -> this.hospitalEntityToHospitalDto(hospital1)).collect(Collectors.toList());
		return hospitalDtoList;
	}

	public HospitalEntity hospitalDtoToHospitalEntity(HospitalDto hospitalDto) {
		return this.modelMapper.map(hospitalDto, HospitalEntity.class);

	}

	public HospitalDto hospitalEntityToHospitalDto(HospitalEntity hospitalEntity) {
		return this.modelMapper.map(hospitalEntity, HospitalDto.class);

	}
}
