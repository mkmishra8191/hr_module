package tech.getarrays.employeemanager.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "appliedLeaves", indexes = @Index(columnList = "id"))
public class AppliedLeave implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    public Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReportingManagerStatus() {
        return reportingManagerStatus;
    }

    public void setReportingManagerStatus(String reportingManagerStatus) {
        this.reportingManagerStatus = reportingManagerStatus;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Employee getEmplyeeProfile() {
        return emplyeeProfile;
    }

    public void setEmplyeeProfile(Employee emplyeeProfile) {
        this.emplyeeProfile = emplyeeProfile;
    }

    public String leaveType;
    public LocalDate fromDate;
    public LocalDate toDate;
    @Column(columnDefinition = "varchar(255) default 'Party '")

    public  String reason;
    @Column(columnDefinition = "varchar(255) default 'Initiated'")
    public  String  reportingManagerStatus;
    public  String employeeName;
    @ManyToOne()
    @JoinColumn(name = "employeeProfile")
    public Employee emplyeeProfile;




}
