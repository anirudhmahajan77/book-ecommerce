package com.book.store.Controller;

import com.book.store.Model.ApplicationUser;
import com.book.store.Model.RequestModel.JwtRequest;
import com.book.store.Model.RequestModel.NewUser;
import com.book.store.Model.RequestModel.UserProfileUpdate;
import com.book.store.Model.ResponseModel.AuthenticatedUser;
import com.book.store.Model.ResponseModel.HeaderMenuUserData;
import com.book.store.Security.JwtUtil;
import com.book.store.Service.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@Tag(name = "User Controller", description = "This is a controller for Different Users" +
        " and their CRUD operations")
public class UserController {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    @Operation(summary = "Authorize Existing User",
            description = "Authorize existing user using their Username and password " +
                    "and send a JWT token in response")
    public ResponseEntity loginUser(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    jwtRequest.getUsername(),
                    jwtRequest.getPassword()
            ));
        } catch (UsernameNotFoundException e) {

            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        }
        String username = jwtRequest.getUsername().trim().toLowerCase();
        ApplicationUser user = this.customUserDetailService.loadUserByUsername(username);
        String token = this.jwtUtil.generateToken(user);

        AuthenticatedUser authenticatedUser = AuthenticatedUser.builder()
                .username(username)
                .token("Bearer " + token)
                .role(user.getRoles().toString())
                .build();
        return new ResponseEntity(authenticatedUser, HttpStatus.OK);
    }


    @PostMapping("/register")
    @Operation(summary = "Register a New User",
            description = "Add a new User in the database and authenticate them with a JWT token")
    public ResponseEntity registerUser(@RequestBody NewUser user) throws IOException {
        customUserDetailService.registerUser(user);
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            ));
        } catch (UsernameNotFoundException e) {

            return new ResponseEntity("User Not Found", HttpStatus.NOT_FOUND);
        }
        String username = user.getUsername().trim().toLowerCase();
        ApplicationUser loggedUser = this.customUserDetailService.loadUserByUsername(username);
        String token = this.jwtUtil.generateToken(loggedUser);
        AuthenticatedUser authenticatedUser = AuthenticatedUser.builder()
                .username(username)
                .token("Bearer " + token)
                .role(loggedUser.getRoles().toString())
                .build();

        return new ResponseEntity(authenticatedUser, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "Get All Users", description = "Get the list of all registered users on the system")
    public ResponseEntity getAllUsers() {
        List<ApplicationUser> result = customUserDetailService.getAllUsers();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get User Details", description = "Get the user details using JWT Token")
    public ResponseEntity getUser(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization").substring(7);
        HeaderMenuUserData user = customUserDetailService.loadUserByToken(token);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping("/update")
    @Operation(summary = "Update User Details", description = "Update the user details using JWT Token")
    public ResponseEntity updateUser(@RequestBody UserProfileUpdate updatedUser, HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.updateUserProfile(updatedUser, token);
        return new ResponseEntity("Done", HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete User", description = "Update the user details using JWT Token")
    public ResponseEntity deleteUser(HttpServletRequest request, HttpServletResponse response){
        String token = request.getHeader("Authorization").substring(7);
        customUserDetailService.deleteUserByToken(token);
        return new ResponseEntity("User Deleted", HttpStatus.OK);
    }
}
