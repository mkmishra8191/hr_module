package tech.getarrays.employeemanager.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.getarrays.employeemanager.model.Department;
import tech.getarrays.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    @Override
    <S extends Employee> S save(S entity);

    void deleteEmployeeById(Long id);

    @Query("select count(*) from Employee u where u.department = :id")
    long Currentcount(Department id);

    Optional<Employee> findEmployeeById(Long id);
    @Query("select u from Employee u where u.reportingTo = :id")
    List<Employee> findAllEmploy(Employee id);

    @Query("select u from Employee u where u.department = :id")
    List<Employee> getEmployeesInfo(Department id);

    @Query("select u from Employee u where u.email = :userName")
    Employee getUserByUserName(@Param("userName") String userName);



}
