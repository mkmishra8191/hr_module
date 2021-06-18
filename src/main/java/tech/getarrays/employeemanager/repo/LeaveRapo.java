package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.getarrays.employeemanager.model.AppliedLeave;

public interface LeaveRapo extends JpaRepository<AppliedLeave,Long> {


}
