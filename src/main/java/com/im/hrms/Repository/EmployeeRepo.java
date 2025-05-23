package com.im.hrms.Repository;

import com.im.hrms.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    public List<Employee> findByEmployeeId(int employeeId);

    public Optional<Employee> findByEmail(String emailId);



}
