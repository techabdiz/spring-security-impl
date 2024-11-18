package com.deaspider.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;

@Data
@Builder
@Entity
@Table(name="t_tokens")
@NoArgsConstructor
@AllArgsConstructor
public class Token extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    /*
     * Validity in minutes
     */
    private Byte validity;

    public boolean isValid() { 
        return LocalDateTime.now()
            .isBefore(this.getCreatedAt().plusMinutes(validity));
    }

    /**
     * TODO: add grants
     */

}
