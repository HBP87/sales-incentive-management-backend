package com.accolite.sim.service;

import com.accolite.sim.entity.CustomUserDetail;
import com.accolite.sim.entity.SalesPerson;
import com.accolite.sim.repository.SalesPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private SalesPersonRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SalesPerson user = this.userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No User");
        }
        return new CustomUserDetail(user);
    }
}
