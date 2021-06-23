package tech.getarrays.employeemanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity

@Table(name = "leaves")
public class Leaves {
    public Leaves(int cl, int pl, int sl) {
        this.cl = cl;
        this.pl = pl;
        this.sl = sl;
    }


    public Leaves() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    private double cl;


    private double takencl;


    private double pl;
    private double takenpl;
    private double sl;
    private double takensl;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "employeeProfile")
    public Employee emplyeeProfile;

    public EmpInfo getEmpInfo() {
        return empInfo;
    }

    public void setEmpInfo(EmpInfo empInfo) {
        this.empInfo = empInfo;
    }

    @Transient
    public EmpInfo  empInfo;




    public Employee getEmplyeeProfile() {
        return emplyeeProfile;
    }

    public void setEmplyeeProfile(Employee emplyeeProfile) {
        this.emplyeeProfile = emplyeeProfile;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
    }

    public double getPl() {
        return pl;
    }

    public void setPl(int pl) {
        this.pl = pl;
    }

    public double getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }
    public double getTakencl() {
        return takencl;
    }

    public void setTakencl(double takencl) {
        this.takencl = takencl;
    }

    public double getTakenpl() {
        return takenpl;
    }

    public void setTakenpl(double takenpl) {
        this.takenpl = takenpl;
    }

    public double getTakensl() {
        return takensl;
    }

    public void setTakensl(double takensl) {
        this.takensl = takensl;
    }

}
