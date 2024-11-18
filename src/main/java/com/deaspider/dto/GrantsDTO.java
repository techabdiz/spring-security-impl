package com.deaspider.dto;

import com.deaspider.models.Grants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GrantsDTO {

    private String name;

    public GrantsDTO(Grants grants) { 
        this.name = grants.getName();
    }

    public Grants toEntity(){
        return Grants.builder()
            .name(name)    
        .build();
    }
    
}
