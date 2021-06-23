package tech.getarrays.employeemanager.model;

import java.time.LocalDate;

public class NewJoinee {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    private String name;
    private LocalDate joiningDate;

    public LocalDate getAppraisalDate() {
        return appraisalDate;
    }

    public void setAppraisalDate(LocalDate appraisalDate) {
        this.appraisalDate = appraisalDate;
    }

    private  LocalDate appraisalDate;

    public NewJoinee() {

    }
}
