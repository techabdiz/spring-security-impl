package com.deaspider.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import com.deaspider.exceptions.DataNotFoundException;
import com.deaspider.exceptions.DuplicateResourceCreationException;
import com.deaspider.models.Grants;
import com.deaspider.models.Role;
import com.deaspider.repos.GrantsRepo;
import com.deaspider.repos.RoleRepo;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class GrantService {

    private GrantsRepo repo;

    private RoleRepo roleRepo;

    public Grants create(String grant) {
        if(repo.existsByName(grant)) {
            throw new DuplicateResourceCreationException(grant + "grant already exists");
        }
        Grants gr = Grants.builder()
                .name(grant)
            .build();
        repo.save(gr);

        return gr;
    }

    public Grants link(String grant, String role) { 
        Grants gr = repo.findByName(grant)
            .orElseThrow(()-> new DataNotFoundException(grant +" AUTH GRANT not found"));
        Role rl = roleRepo.findByName(role)
            .orElseThrow(()-> new DataNotFoundException(role +" ROLE not found"));
        List<Grants> roleGrants = rl.getGrants();
        if(Objects.isNull(roleGrants)) { 
            roleGrants = new ArrayList<>();
        }
        roleGrants.add(gr);

        List<Role> roles = gr.getRoles();
        if(Objects.isNull(roles)) { 
            roles = new ArrayList<>();
        };
        roles.add(rl);
        gr.setRoles(roles);
        gr = repo.save(gr);
        return gr;
    }

    public List<Grants> getAll() { 
        return repo.findAll();
    }

}
