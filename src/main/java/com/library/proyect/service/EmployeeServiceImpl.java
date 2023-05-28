package com.library.proyect.service;

import com.library.proyect.model.EmployeeEntity;
import com.library.proyect.repository.employee.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The type Employee service.
 */
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Method used for UserDetails and relationship Username,
     * Password and ROLE
     *
     * @param username used for fetch user in the DB
     * @return UserDetails
     * @throws UsernameNotFoundException exception when user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return employeeRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    /**
     * This method is used for load UserByUsername
     *
     * @param username used for fetch user in the DB
     * @return EmployeeEntity if user is found
     * @see #loadUserByUsername(String)
     */
    @Override
    public Optional<EmployeeEntity> findByUsername(String username) {
        return this.employeeRepository.findByUsername(username);
    }
}
