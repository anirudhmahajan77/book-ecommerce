package com.book.store.Service;

import com.book.store.Model.ApplicationUser;
import com.book.store.Model.RequestModel.NewUser;
import com.book.store.Model.Role;
import com.book.store.Repository.RoleRepository;
import com.book.store.Repository.UserRepository;
import com.book.store.Security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void registerUser(NewUser dummy){
        Set<Integer> list = dummy.getAuthorities();
        Set<Role> roles = new HashSet<>();
        roles.addAll(roleRepository.findAllById(list));

        ApplicationUser user = new ApplicationUser(dummy.getUsername(),
                passwordEncoder.encode(dummy.getPassword()),
                roles);
        userRepository.save(user);
    }
    @Override
    public ApplicationUser loadUserByUsername(String username) throws UsernameNotFoundException {
        ApplicationUser user = userRepository.findUserByUsername(username);
        return user;
    }

    public List<ApplicationUser> getAllUsers() {
        return userRepository.findAll();
    }
}
