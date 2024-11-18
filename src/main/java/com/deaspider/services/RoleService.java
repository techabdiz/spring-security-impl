package com.deaspider.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import com.deaspider.exceptions.DataNotFoundException;
import com.deaspider.exceptions.DuplicateResourceCreationException;
import com.deaspider.models.Role;
import com.deaspider.models.User;
import com.deaspider.repos.RoleRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {

    private RoleRepo repo;
    private UserService userService;

    public Role create(String role) {
        if(repo.existsByName(role)) {
            throw new DuplicateResourceCreationException(role + "role already exists");
        }
        Role rl = Role.builder()
                .name(role)
            .build();
        rl = repo.save(rl);
        return rl;
    }

    public Role link(String role, String username) { 
        User user = userService.getByUsername(username);
        Role rl = repo.findByName(role)
            .orElseThrow(()-> new DataNotFoundException("invalid role: " + role));
        List<Role> userRoles = user.getRoles();
        if(Objects.isNull(userRoles)) { 
            userRoles = new ArrayList<>();
        }
        userRoles.add(rl);
        user.setRoles(userRoles);
        List<User> users = rl.getUsers();
        if(Objects.isNull(users)) { 
            users = new ArrayList<>();
        }
        users.add(user);
        rl.setUsers(users);
        rl = repo.save(rl);
        return rl;
    }

    public List<Role> getAll() { 
        return repo.findAll();
    }

}
