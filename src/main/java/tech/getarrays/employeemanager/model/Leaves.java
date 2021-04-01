package tech.getarrays.employeemanager.model;


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

    private double pl;

    private double sl;





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
}
