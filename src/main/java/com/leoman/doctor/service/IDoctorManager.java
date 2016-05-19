package com.leoman.doctor.service;

import com.leoman.common.service.GenericManager;
import com.leoman.doctor.entity.Doctor;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDoctorManager extends GenericManager<Doctor> {
	public Doctor findById(String id) throws Exception ;

	public List<Doctor> findAllDoctor();

	public Page<Doctor> findAll(Doctor doctor, Integer currentPage, Integer pageSize) throws Exception ;
}
