package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.exception.UserNotFoundException;
import tech.getarrays.employeemanager.model.*;
import tech.getarrays.employeemanager.repo.*;

import javax.mail.Session;
import javax.swing.text.Document;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;


@Service
@Transactional
public class EmployeeService implements Serializable {
    private final EmployeeRepo employeeRepo;
    private final DepartmentRepo departmentRepo;
    private final LeaveRapo leaveRapo;
    private final LeavesRapo leavesRapo;







    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo, DepartmentRepo departmentRepo, LeaveRapo leaveRapo, LeavesRapo leavesRapo) {
        this.employeeRepo = employeeRepo;
        this.departmentRepo = departmentRepo;
        this.leaveRapo = leaveRapo;
        this.leavesRapo = leavesRapo;
    }
    public List<Information> findAllDepartment() throws IOException {

        List DepInfo = new ArrayList<>();



        List<Department> departments=departmentRepo.findAll();

        departments.forEach(department -> {

            Information info= new Information();
            info.setId(department.getId());
            info.setName(department.getName());

            info.setStrength(5L);
            info.setCount(employeeRepo.Currentcount(department));

            DepInfo.add(info);


        });




          return DepInfo;

    }
    public AppliedLeave applyLeavep(AppliedLeave appliedLeave) {

        return leaveRapo.save(appliedLeave);
    }
    public List<Employee> findAllEmployeess(Employee id) {
        return employeeRepo.findAllEmploy(id);
    }
    public Employee saveEmployees(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<EmpInfo> findAllEmployeesInfo(Department id) {

        List EmpInfo = new ArrayList<>();



        List<Employee> employees=employeeRepo.getEmployeesInfo(id);

        employees.forEach(employee -> {

            EmpInfo info= new EmpInfo();
            info.setId(employee.getId());
            info.setName(employee.getName());
            info.setDesignation(employee.getDesignation());
            info.setSkills(employee.getSkill());
            info.setDepartment(employee.getDepartment());

            EmpInfo.add(info);


        });






        return EmpInfo;
    }

    public  EmpInfo getUser(Employee employee){
        EmpInfo info = new EmpInfo();
        info.setId(employee.getId());
        info.setName(employee.getName());
        info.setDesignation(employee.getJoiningDate());
        info.setSkills(employee.getSkill());
        info.setDepartment(employee.getDepartment());

      return   info;

    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }
    public List<Leaves> findAllLeavse() {
        List<Leaves> leaves =leavesRapo.findAll();
        leaves.forEach(employee -> {

            EmpInfo info = new EmpInfo();
            info.setId(employee.emplyeeProfile.getId());
            info.setName(employee.emplyeeProfile.getName());
            info.setDesignation(employee.emplyeeProfile.getJoiningDate());
            info.setSkills(employee.emplyeeProfile.getSkill());
            info.setDepartment(employee.emplyeeProfile.getDepartment());

            employee.setEmpInfo(info);



        });
        return leaves;

    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public Employee findEmployeeById(Long id) {


        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }
    public Department findDepartmentById(Long id) {
        return departmentRepo.findEmployeeById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }


    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeById(id);
    }
}