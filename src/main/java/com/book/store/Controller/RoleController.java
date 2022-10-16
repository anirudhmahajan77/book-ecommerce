package com.book.store.Controller;

import com.book.store.Model.RequestModel.NewRole;
import com.book.store.Model.Role;
import com.book.store.Service.RoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/role")
@Tag(name = "Role Controller", description = "This is a controller for User Roles and it's CRUD operations")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/{id}")
    public ResponseEntity getRoleById(@PathVariable("id") Integer id){
        Role result = roleService.getRoleById(id);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity getAllRoles(){
        List<Role> result = roleService.getAllRoles();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/add")
    //@RolesAllowed("ADMIN")
    public ResponseEntity addNewRole(@RequestBody NewRole role){
        roleService.addRole(role);
        return new ResponseEntity("Done", HttpStatus.OK);
    }

    @PutMapping("/update")
    @RolesAllowed("ADMIN")
    public ResponseEntity addNewRole(@RequestBody Role role){
        roleService.updateRole(role);
        return new ResponseEntity("Done", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed("ADMIN")
    public ResponseEntity deleteRole(@PathVariable("id") Integer id){
        roleService.deleteRoleById(id);
        return new ResponseEntity("Role Deleted!", HttpStatus.OK);
    }

}
