package com.example.security.service.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "DULCERIA_USER")
@Getter
@Setter
@NoArgsConstructor
public class DulceriaUserEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_USER")
    private Long idUser;
    @Column(name = "NAME")
    private String name;
    @Column(name = "PASSWORD")
    private String password;
    @OneToMany(mappedBy = "user")
    private List<DulceriaUserRolesEntity> roles;

    public DulceriaUserEntity(Long idUser){
        this.idUser = idUser;
    }
}
