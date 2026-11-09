package com.unicartagena.apihexagonal.infrastructure.adapters.out.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "paises")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String presidente;
    private String continente;
    private String nombreHimno;
    private Integer numHabitantes;
    private Integer numDepartamentos;
    private Integer numMunicipios;
    private String idiomaPpal;
    private Integer numUniversidades;
    private String tipoDemocracia;

}