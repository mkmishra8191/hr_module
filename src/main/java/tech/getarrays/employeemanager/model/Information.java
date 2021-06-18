package tech.getarrays.employeemanager.model;

public class Information {

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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getStrength() {
        return strength;
    }

    public void setStrength(Long strength) {
        this.strength = strength;
    }

    private Long id;
    private String name;
    private Long count;
    private  Long strength;
    @Override
    public  String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count='" + count + '\'' +


                ", email='" + strength+ '\'' +

                '}';
    }



}
