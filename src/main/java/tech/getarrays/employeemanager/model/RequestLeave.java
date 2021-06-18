package tech.getarrays.employeemanager.model;

import java.time.LocalDate;

public class RequestLeave {

    public String getTypee() {
        return typee;
    }

    public void setTypee(String typee) {
        this.typee = typee;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    private String typee;

    private LocalDate toDate;
    private LocalDate fromDate;

    public Double getLeaves() {
        return leaves;
    }

    public void setLeaves(Double leaves) {
        this.leaves = leaves;
    }

    private Double leaves;

    public RequestLeave() {

    }
}
