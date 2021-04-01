package tech.getarrays.employeemanager.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.model.AuthRequest;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.model.JwtResponse;
import tech.getarrays.employeemanager.service.EmployeeService;
import tech.getarrays.employeemanager.service.MyUserDetailsService;
import tech.getarrays.employeemanager.util.JwtUtil;

import java.util.List;

@RestController

public class EmployeeResource {
    private final EmployeeService employeeService;
   @Autowired
   private AuthenticationManager authenticationManager;
   @Autowired
   private MyUserDetailsService myUserDetailsService;
   @Autowired
   private JwtUtil jwtTokenUtil;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST )

    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception{

        try {


        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
    );
        }
        catch (BadCredentialsException e){
            throw new Exception ("Incorrect username or password", e);
        }
        final UserDetails userDetails = myUserDetailsService
                .loadUserByUsername(authRequest.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/all")
     public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = employeeService.findAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
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
