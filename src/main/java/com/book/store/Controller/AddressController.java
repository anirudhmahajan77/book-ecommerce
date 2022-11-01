package com.book.store.Controller;

import com.book.store.Model.Address;
import com.book.store.Model.RequestModel.AddAddress;
import com.book.store.Service.CustomUserDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@Tag(name = "Address Controller",
        description = "This is a controller for Different Addresses and their CRUD operations")
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    CustomUserDetailService customUserDetailService;

    @PostMapping("/add")
    @Operation(summary = "Add Address",
            description = "Add New Address to an existing user profile in the database")
    public ResponseEntity addUserAddress(@RequestBody AddAddress newAddress, HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        System.out.println("===Token: "+token);
        customUserDetailService.addUserAddress(newAddress, token);
        return new ResponseEntity("Done", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Existing Address",
            description = "Update the details of an address using the address ID")
    public ResponseEntity updateUserAddress(@PathVariable("id") Long id, @RequestBody AddAddress newAddress) {
        customUserDetailService.updateUserAddress(id, newAddress);
        return new ResponseEntity("Done", HttpStatus.OK);
    }

    @GetMapping("/all")
    //@CrossOrigin(allowedHeaders = {"Authorization","Access-Control-Allow-Origin"})
    @Operation(summary = "Get All Address", description = "Get All Address in the System")
    public ResponseEntity getAllAddress(HttpServletRequest request, HttpServletResponse response) {
        List<Address> result = customUserDetailService.getAllAddress();
        String token = request.getHeader("Authorization");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type,Content-Range,Range");
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Address by ID",
            description = "Get the Address with the given ID")
    public ResponseEntity getAddressById(@PathVariable("id") Long id) {
        Address result = customUserDetailService.getAddress(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Address by ID", description = "Delete the Address with the given ID")
    public ResponseEntity deleteAddressById(@PathVariable("id") Long id, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        customUserDetailService.deleteAddress(id, token);
        return new ResponseEntity("Address Deleted", HttpStatus.OK);
    }

}
