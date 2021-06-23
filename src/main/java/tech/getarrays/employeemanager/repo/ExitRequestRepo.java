package tech.getarrays.employeemanager.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;
import tech.getarrays.employeemanager.model.AppliedLeave;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.ExitRequest;

import java.util.List;

public interface ExitRequestRepo extends JpaRepository<ExitRequest,Long> {

    @Query("select u from ExitRequest u where u.state = :Approved")
    List<ExitRequest> getExit(@Param("Approved") String Approved);


}
