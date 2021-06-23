package tech.getarrays.employeemanager.resource;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.filters.JwtRequestFilter;
import tech.getarrays.employeemanager.model.*;
import tech.getarrays.employeemanager.repo.*;
import tech.getarrays.employeemanager.service.EmailService;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.MyUserDetailsService;

import tech.getarrays.employeemanager.util.JwtUtil;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;



@RestController
@RequestMapping("v1")

public class EmployeeResource {
    private final DepartmentRepo departmentRepo;
    private final EmployeeService employeeService;
    private  final LeaveRapo leaveRapo;
    private  final LeavesRapo leavesRapo;
    private final EmailService emailService;
    private  final EmployeeRepo employeeRepo;
    private final ExitRequestRepo exitRequestRepo;

   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private MyUserDetailsService myUserDetailsService;
   @Autowired
   private JwtUtil jwtTokenUtil;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    private Employee Employee;




    public EmployeeResource(DepartmentRepo departmentRepo, EmployeeService employeeService, LeaveRapo leaveRapo, LeavesRapo leavesRapo, EmailService emailService, EmployeeRepo employeeRepo, ExitRequestRepo exitRequestRepo) {
        this.departmentRepo = departmentRepo;
        this.employeeService = employeeService;
        this.leaveRapo = leaveRapo;

        this.leavesRapo = leavesRapo;
        this.emailService = emailService;
        this.employeeRepo = employeeRepo;
        this.exitRequestRepo = exitRequestRepo;
    }
    @CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.POST )

    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {
        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authRequest.getUserName());


        try {


            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }



        final String jwt = jwtTokenUtil.generateToken(userDetails);
        LoginResponse loginResponse = new LoginResponse(jwt,"Bearer",MyUserDetailsService.authorities.toString());





            return new ResponseEntity(loginResponse, HttpStatus.OK);

    }
    @RequestMapping(value = "/employeescount",method = RequestMethod.GET)
    public ResponseEntity <List<Information>> getAllEmployees () throws IOException {
        List<Information> information=employeeService.findAllDepartment();
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/employeesinformation/{id}",method = RequestMethod.GET)
    public ResponseEntity <List<EmpInfo >> getEmployeesInfo (@PathVariable("id") long id) throws IOException {
        List<EmpInfo> information=employeeService.findAllEmployeesInfo(employeeService.findDepartmentById(id));
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/allemployeesinformation",method = RequestMethod.GET)
    public ResponseEntity <List<EmpInfo >> getallEmployeesInfo () throws IOException {
        List<EmpInfo> information=employeeService.findAllEmployeesInfo();
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/employeesinformations/{skill}",method = RequestMethod.GET)
    public ResponseEntity <List<EmpInfo >> getEmployeesInfo (@PathVariable("skill") String skill) throws IOException {
        List<EmpInfo> information=employeeService.getEmployeesInformations(skill);
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/newjoineeinformation",method = RequestMethod.GET)
    public ResponseEntity <List<NewJoinee >> getNewJoinee () throws IOException {
        List<NewJoinee> information=employeeService.getNewJoineeInfo();
        return new ResponseEntity(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/getemployees",method = RequestMethod.GET)
    public ResponseEntity <List<EmpInfo >> gettEmployees () throws IOException {
        List<EmpInfo> information=employeeService.getEmployeesInfo(MyUserDetailsService.userr);
        return new ResponseEntity<>(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/exit",method = RequestMethod.GET)
    public ResponseEntity <List<NewJoinee>> gettExit () throws IOException {
        List<NewJoinee> information=employeeService.getExit();
        return new ResponseEntity(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/exits",method = RequestMethod.GET)
    public ResponseEntity <List<ExitRequests>> gettExits () throws IOException {
        List<ExitRequests> information= employeeService.getExits();
        return new ResponseEntity(information, HttpStatus.OK);
    }
    @RequestMapping(value = "/department",method = RequestMethod.GET)
     public ResponseEntity  getAllDepartment () {

        return new ResponseEntity(departmentRepo.findAll(), HttpStatus.OK);
    }
    @GetMapping("/subordinate/{id}")
    public ResponseEntity<List<Employee>> getAllSubordinates (@PathVariable("id") Long id) {
        List<Employee> employees = employeeService.findAllEmployeess(employeeService.findEmployeeById(id));
        return new ResponseEntity(employees.toString(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity(employee.toString(), HttpStatus.OK);
    }
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmlpoyee = employeeService.saveEmployees(employee);
        return new ResponseEntity<>(newEmlpoyee, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/leave", method = RequestMethod.POST )

    public ResponseEntity<AppliedLeave> addEmployee(@RequestBody RequestLeave requestLeave) {

        AppliedLeave appliedLeave=new AppliedLeave();
        appliedLeave.setEmplyeeProfile(MyUserDetailsService.userr);
        appliedLeave.setLeaveType(requestLeave.getTypee());
        appliedLeave.setFromDate(requestLeave.getFromDate());
        appliedLeave.setToDate(requestLeave.getToDate());
        appliedLeave.setReportingManagerStatus("Waiting");
        appliedLeave.setEmployeeName(MyUserDetailsService.userr.getName());

        AppliedLeave leave = employeeService.applyLeavep(appliedLeave);
        if(leave!=null && requestLeave.getTypee().equals("cl")){
            emailService.RequestStatus(MyUserDetailsService.userr);
            leavesRapo.updateLeavescl(requestLeave.getLeaves(), MyUserDetailsService.userr);


        }
        if(leave!=null && requestLeave.getTypee().equals("pl")){
            emailService.RequestStatus(MyUserDetailsService.userr);
            leavesRapo.updateLeavespl(requestLeave.getLeaves(), MyUserDetailsService.userr);


        }
        if(leave!=null && requestLeave.getTypee().equals("sl")){
            emailService.RequestStatus(MyUserDetailsService.userr);
            leavesRapo.updateLeavessl(requestLeave.getLeaves(), MyUserDetailsService.userr);


        }
        return new ResponseEntity<>(leave, HttpStatus.CREATED);
    }
    @GetMapping("/appliedleaves/{id}")
    public  ResponseEntity<List<AppliedLeave>> getLeavesdetails(@PathVariable("id") Long id){
        List<AppliedLeave> leaves = leaveRapo.findApplyLeaves(employeeRepo.findEmployeeById(id));

        return new ResponseEntity<>(leaves, HttpStatus.OK);

    }
    @RequestMapping(value = "/approveleave", method = RequestMethod.POST )
    public  ResponseEntity<AppliedLeave> approveLeave(@RequestBody ApproveLeaveRequest approveLeaveRequest){
        leaveRapo.updateLeavescl(approveLeaveRequest.getReportingManagerStatus(),approveLeaveRequest.getId());
        Optional<AppliedLeave> appliedLeave = leaveRapo.findLeavesById(approveLeaveRequest.getId());
        return new ResponseEntity(appliedLeave, HttpStatus.OK);

    }


    @GetMapping("/leaves")
   public  ResponseEntity<List<Leaves>> getLeavesdetails(){
        List<Leaves> leaves = employeeService.findAllLeavse();

        return new ResponseEntity<>(leaves, HttpStatus.OK);

   }
    @GetMapping("/user")
    public  ResponseEntity<Leaves> getLeavedetails(HttpServletRequest request){

        EmpInfo info=employeeService.getUser(MyUserDetailsService.userr);

       Leaves leaves = leavesRapo.findEmploy(MyUserDetailsService.userr);
       leaves.setEmpInfo(info);




        return new ResponseEntity(leaves, HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = employeeService.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
