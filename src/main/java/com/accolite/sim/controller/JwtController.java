package com.accolite.sim.controller;

import com.accolite.sim.dto.JwtRequest;
import com.accolite.sim.dto.JwtResponse;
import com.accolite.sim.helper.JwtUtil;
import com.accolite.sim.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@CrossOrigin("*")
@RestController
public class JwtController {

    @Autowired
    private CustomUserDetailService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private AuthenticationManager authenticationManager;

    @CrossOrigin("*")
    @RequestMapping(value = "/getToken", method = RequestMethod.POST)
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new EntityNotFoundException("Bad Credentials");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());;
        String token = this.jwtUtil.generateToken(userDetails);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        List<String> authoritiesInString = authorities.stream().map((Object::toString)).collect(Collectors.toList());
        System.out.println("JWT token: " + token);
        return ResponseEntity.ok(new JwtResponse(token,authoritiesInString));
    }
}
