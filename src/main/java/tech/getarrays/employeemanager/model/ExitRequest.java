package tech.getarrays.employeemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "exitRequest", indexes = @Index(columnList = "id"))
public class ExitRequest implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    public Long id;
    public  String stage;
    public  String state;
    public  String coments;
    public LocalDate exitDate;
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "employeeProfile")
    public Employee emplyeeProfile;
    @Transient
    public EmpInfo  empInfo;

    public ExitRequest() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getComents() {
        return coments;
    }

    public void setComents(String coments) {
        this.coments = coments;
    }

    public LocalDate getExitDate() {
        return exitDate;
    }

    public void setExitDate(LocalDate exitDate) {
        this.exitDate = exitDate;
    }

    public Employee getEmplyeeProfile() {
        return emplyeeProfile;
    }

    public void setEmplyeeProfile(Employee emplyeeProfile) {
        this.emplyeeProfile = emplyeeProfile;
    }

    public EmpInfo getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(EmpInfo empInfo) {
        this.empInfo = empInfo;
    }
}
