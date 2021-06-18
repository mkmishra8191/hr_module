package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.employeemanager.model.Department;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.Leaves;

import java.util.List;
import java.util.Optional;

public interface LeavesRapo extends JpaRepository<Leaves,Long> {

    Optional<Department> findLeavesById(Long id);
    @Query("select u from Leaves u where u.emplyeeProfile = :id")
    Leaves findEmploy(Employee id);
    @Transactional
    @Modifying
    @Query("update Leaves u set u.cl = u.cl- :leaves where u.emplyeeProfile = :id")
    void updateLeavescl(Double leaves,Employee id);
    @Transactional
    @Modifying
    @Query("update Leaves u set u.sl = u.sl- :leaves where u.emplyeeProfile = :id")
    void updateLeavessl(Double leaves,Employee id);
    @Transactional
    @Modifying
    @Query("update Leaves u set u.pl = u.pl- :leaves where u.emplyeeProfile = :id")
    void  updateLeavespl(Double leaves,Employee id);



}
