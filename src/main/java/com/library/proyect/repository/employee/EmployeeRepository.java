package com.library.proyect.repository.employee;

import com.library.proyect.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {

  Optional<EmployeeEntity> findByUsername(String username);

}
