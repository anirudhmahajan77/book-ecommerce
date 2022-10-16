package com.book.store.Controller;

import com.book.store.Model.ApplicationUser;
import com.book.store.Model.RequestModel.JwtRequest;
import com.book.store.Model.RequestModel.NewUser;
import com.book.store.Security.JwtUtil;
import com.book.store.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/auth/dummy")
    @RolesAllowed("ADMIN")
    public ResponseEntity dummy() {
        return new ResponseEntity("Token is Working", HttpStatus.OK);
    }

    @GetMapping("/auth/dummytwo")
    @PreAuthorize("hasAnyAuthority('CUSTOMER')")
    public ResponseEntity dummyTwo() {
        return new ResponseEntity("Token is Working", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity loginUser(@RequestBody JwtRequest jwtRequest) throws Exception {
        try{
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));
        } catch (UsernameNotFoundException e){

            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        }
        ApplicationUser user = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtil.generateToken(user);


        return new ResponseEntity("Bearer "+token, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@RequestBody NewUser user){
        customUserDetailService.registerUser(user);
        return new ResponseEntity("Done", HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllUsers(){
        List<ApplicationUser> result = customUserDetailService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
