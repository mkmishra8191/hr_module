package tech.getarrays.employeemanager.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    void deleteEmployeeById(Long id);

    Optional<Employee> findEmployeeById(Long id);

    @Query("select u from Employee u where u.reportingTo = :id")
    Optional<List<Employee>> subOrdinates(Long id);

    @Query("select u from Employee u where u.email = :userName")
    Employee getUserByUserName(@Param("userName") String userName);



}
