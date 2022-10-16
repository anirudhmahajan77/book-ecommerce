package com.book.store.Model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role{
    @Id
    @Column(name = "role_id")
    @SequenceGenerator(
            name = "role_sequence",
            sequenceName = "role_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_sequence"
    )
    private Integer id;

    @Column(nullable = false, length = 50, unique = true)
    private String name;

    @Override
    public String toString() {
        return this.name;
    }
}
