package com.deaspider.dto;

import java.util.List;
import java.util.Objects;

import com.deaspider.models.Role;

import lombok.Data;

@Data
public class RoleDTO {

    private String name;

    private List<GrantsDTO> grants;

    private List<UserDTO> users;


    public RoleDTO(Role role) { 
        this.name = role.getName();
        if(Objects.nonNull(role.getGrants())) { 
            this.grants = role.getGrants() 
            .stream().map(g-> new GrantsDTO(g)).toList();
        }

        if(Objects.nonNull(role.getUsers())) { 
            this.users = role.getUsers()
                .stream().map(u-> new UserDTO(u)).toList();
        }
        
    }

    public Role toEntity(){
        return Role.builder()
            .name(name)
            .grants(this.grants.stream().map(g-> g.toEntity()).toList())
        .build();
    }

}
