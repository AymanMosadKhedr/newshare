package com.userManagement.Controller;


import com.userManagement.Service.UserService;
import com.userManagement.Service.UserServiceDetailsImpl;
import com.userManagement.jwt.JwtTokenUtil;
import com.userManagement.model.JwtRequest;
import com.userManagement.model.JwtResponse;
import com.userManagement.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.userManagement.model.user;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("http://localhost:8081/")
public class UserController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceDetailsImpl userServiceDetailsImpl;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody user user) {
        System.out.println(user.getToken());
        if (userService.findByUsername(user.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody JwtRequest authenticationRequest, HttpServletRequest request) throws Exception {
        System.out.println(authenticationRequest.getUsername());
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userServiceDetailsImpl.loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        //System.out.print(request.getSession().getAttribute("Sys_Password"));

        //User user = userDetailsService.findByUsername(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }


    @DeleteMapping("")
    public void deleteByUsername() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        user my_user = (user) authentication.getCredentials();
        //System.out.println("test");
        //System.out.println(my_user.toString());
        userService.deleteByUsername(my_user.getUsername());
    }





    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(userService.findAll());


    }





    @RequestMapping(value = "",method = RequestMethod.PUT)
    public ResponseEntity<?> Edit(@RequestBody user user) {
        return ResponseEntity.ok(UserService.Update(user));
    }



}
