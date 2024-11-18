package com.deaspider.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="t_users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String mobile;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user")
    private List<Token> token;

    @ManyToMany
    @JoinTable(name="t_user_roles", 
        joinColumns = @JoinColumn(name="user_id"), 
        inverseJoinColumns = @JoinColumn(name="role_id"))
    private List<Role> roles;
}

