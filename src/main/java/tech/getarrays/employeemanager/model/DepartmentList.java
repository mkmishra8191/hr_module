package tech.getarrays.employeemanager.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DepartmentList {

    public DepartmentList() {

    }

    public Object getInformation() {
        return information;
    }

    public void setInformation(Object information) {
        this.information = information;
    }
    @JsonProperty("information")
    private Object information;

    public DepartmentList(Object information) {
        this.information = information;
    }
    @Override
    public String toString() {
        return "information:" + information;

    }


}
