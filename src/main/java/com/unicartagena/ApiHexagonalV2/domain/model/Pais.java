package com.unicartagena.ApiHexagonalV2V2.domain.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pais {
    private Long id;
    private String nombre;
    private String presidente;
    private String continente;
    private String nombreHimno;       // (Corregido de hombreHimno)
    private Integer numHabitantes;
    private Integer numDepartamentos;
    private Integer numMunicipios;
    private String idiomaPpal;
    private Integer numUniversidades; // (Corregido de numUnivesidades)
    private String tipoDemocracia;
}