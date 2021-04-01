package tech.getarrays.employeemanager.model;

import org.hibernate.mapping.Constraint;
import org.hibernate.tool.schema.internal.StandardForeignKeyExporter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    private String name;

    @Column(unique = true)
    private String email;
    private String department;


    private String phone;
    private String designation;


    private Long reportingTo;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    private  boolean enabled;
    private LocalDate joiningDate;
    private String password;
    private String skill;

    public Employee() {}

    public Employee(String name, String email,LocalDate joiningDate, String password,Long reportingTo, String department, String phone, String designation, String skill) {
        this.joiningDate = joiningDate;
        this.reportingTo = reportingTo;
        this.password = password;
        this.name = name;
        this.email = email;
        this.department = department;
        this.phone = phone;
        this.designation = designation;
        this.skill = skill;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPhone() {
        return phone;
    }



    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Long getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(Long reportingTo) {
        this.reportingTo = reportingTo;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", jobTitle='" + department + '\'' +
                ", phone='" + phone + '\'' +
                ", imageUrl='" + designation + '\'' +
                '}';
    }
}
