package com.example.security.service.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "DULCERIA_ROLES")
@Getter
@Setter
@NoArgsConstructor
public class DulceriaRolesEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_ROL")
    private Long idRol;
    @Column(name = "NAME")
    private String name;
    @OneToMany(mappedBy = "rol")
    private List<DulceriaUserRolesEntity> users;

    public DulceriaRolesEntity(Long idRol){
        this.idRol = idRol;
    }
}
