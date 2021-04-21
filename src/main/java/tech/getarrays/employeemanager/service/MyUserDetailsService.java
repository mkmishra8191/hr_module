package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repo.EmployeeRepo;

import java.util.HashSet;
import java.util.Set;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private  EmployeeRepo employeeRepo;
    public static  Long  idd;
    public static Employee userr;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee user = this.employeeRepo.getUserByUserName(username);
         this.userr = user;
         System.out.println(idd);
        if (user==null){

            throw  new UsernameNotFoundException("can not find user");
        }



        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }
    private Set getAuthority(Employee user) {
        Set authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
}
