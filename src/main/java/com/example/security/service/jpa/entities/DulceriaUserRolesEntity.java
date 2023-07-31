package com.example.security.service.jpa.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DULCERIA_USER_ROLES")
@Getter
@Setter
@NoArgsConstructor
public class DulceriaUserRolesEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_USER_ROL")
    private Long idUserRol;
    @ManyToOne
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID_USER")
    private DulceriaUserEntity user;
    @ManyToOne
    @JoinColumn(name = "ID_ROL", referencedColumnName = "ID_ROL")
    private DulceriaRolesEntity rol;

    public DulceriaUserRolesEntity(Long idUserRol){
        this.idUserRol = idUserRol;
    }
}
