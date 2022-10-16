package com.book.store.Service;

import com.book.store.Model.RequestModel.NewRole;
import com.book.store.Model.Role;
import com.book.store.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role getRoleById(Integer id) {
        Optional<Role> result = roleRepository.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        return null;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void addRole(NewRole role) {
        Role neRole = Role.builder()
                .name(role.getName())
                .build();
        roleRepository.save(neRole);
    }

    public void deleteRoleById(Integer id) {
        roleRepository.deleteById(id);
    }

    public void updateRole(Role role) {
        roleRepository.save(role);
    }
}
