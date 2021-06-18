package tech.getarrays.employeemanager.model;





import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Set;


@Entity
@Table(name = "employee", indexes = @Index(columnList = "id"))
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false, name = "id")
    public Long id;
    private String name;

    @Column(unique = true)
    private String email;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "employee_department",joinColumns = {@JoinColumn(name="id")},inverseJoinColumns = {@JoinColumn(name="department_id")})

    private  Department department;


    private String phone;
    private String designation;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ReportingTo")
    private Employee reportingTo;
    @OneToMany(mappedBy="reportingTo")
    private Set<Employee> subordinates ;






    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)

    @JoinTable(name = "user_roles",joinColumns = {@JoinColumn(name="id")},inverseJoinColumns = {@JoinColumn(name="role_id")})

    private  Set<Role> roles;

    private  boolean enabled;

    public LocalDate joiningDate;

    public LocalDate getIncrementDate() {

        LocalDate temp = LocalDate.now();
        int monthDiff = Period.between(temp, LocalDate.now()).getMonths();
        int monthAdd = (monthDiff / 12) * 12 + 12;

      return temp.plusMonths(monthAdd).withDayOfMonth(1);


    }


    @JsonIgnore
    private String password;
    private String skill;

    public Employee() {}


    public Employee(String name, String email,LocalDate joiningDate, String password,String phone, String designation, String skill) {
        this.joiningDate = joiningDate;

        this.password = password;
        this.name = name;
        this.email = email;

        this.phone = phone;
        this.designation = designation;
        this.skill = skill;

    }
    public Employee getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(Employee reportingTo) {
        this.reportingTo = reportingTo;
    }

public Long getId(){

        return this.id;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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
    private String getRoles(Set<Role> roles){

        final String[] rolle = {""};

        roles.forEach(role->{ rolle[0] +=  role.getName();});

        return rolle[0];

    }

    public String getJoiningDate() {
        return LocalDate.now().toString();
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



                '}';
    }
}
