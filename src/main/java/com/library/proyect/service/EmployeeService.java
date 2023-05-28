package com.library.proyect.service;

import com.library.proyect.model.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

  Optional<EmployeeEntity> findByUsername(String username);

}
