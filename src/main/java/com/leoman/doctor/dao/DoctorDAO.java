package com.leoman.doctor.dao;

import com.leoman.common.dao.IBaseJpaRepository;
import com.leoman.doctor.entity.Doctor;

public interface DoctorDAO extends IBaseJpaRepository<Doctor> {

	public Doctor findById(String id);
}
