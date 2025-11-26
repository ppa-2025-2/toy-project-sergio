package com.example.demo.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.RoleRepository;
import com.example.demo.Repository.Entity.Role;

@Service
public class RoleBusiness {

    private RoleRepository roleRepository;
    private Set<String> defaultRoles;

    public RoleBusiness(RoleRepository roleRepository,
            @Value("${app.user.default.roles}") 
            Set<String> defaultRoles
    ){
        this.roleRepository = roleRepository;
        this.defaultRoles = defaultRoles;
    }

    public Set<Role> listaRolesPorNome(Collection<String> names){
        Set<Role> roles = new HashSet<>();
        
        roles.addAll(roleRepository.findByNameIn(defaultRoles));

        Set<Role> additionalRoles = roleRepository.findByNameIn(names);
        if (additionalRoles.size() != names.size()) {
            throw new IllegalArgumentException("Alguns papéis não existem");
        }

        if (roles.isEmpty()) {
            throw new IllegalArgumentException("O usuário deve ter pelo menos um papel");
        }

        return roles;
    }

}
