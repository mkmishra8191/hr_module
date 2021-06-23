package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import tech.getarrays.employeemanager.model.AppliedLeave;
import tech.getarrays.employeemanager.model.Employee;

import java.util.List;
import java.util.Optional;

public interface LeaveRapo extends JpaRepository<AppliedLeave,Long> {

    Optional<AppliedLeave> findLeavesById(Long id);
    @Query("select u from AppliedLeave u where u.emplyeeProfile = :id")
    List<AppliedLeave> findApplyLeaves(Optional<Employee> id);
    @Transactional
    @Modifying
    @Query("update AppliedLeave u set u.reportingManagerStatus = :status where u.id = :id")
    void updateLeavescl(String status,Long id);



}
