package com.unicartagena.apihexagonal.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ciudad {
    private Long id;
    private String nombre;
    private String paisPertenece;
    private String alcalde;
    private String clima;
}