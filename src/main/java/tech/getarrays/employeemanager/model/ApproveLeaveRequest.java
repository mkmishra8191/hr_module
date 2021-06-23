package tech.getarrays.employeemanager.model;

public class ApproveLeaveRequest {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReportingManagerStatus() {
        return reportingManagerStatus;
    }

    public void setReportingManagerStatus(String reportingManagerStatus) {
        this.reportingManagerStatus = reportingManagerStatus;
    }

    private String reportingManagerStatus;

    public ApproveLeaveRequest() {

    }
}
