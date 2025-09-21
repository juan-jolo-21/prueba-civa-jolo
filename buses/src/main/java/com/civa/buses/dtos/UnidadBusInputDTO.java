package com.civa.buses.dtos;

import lombok.Data;

@Data
public class UnidadBusInputDTO {

    private Long numero_bus;
    private String placa;
    private String fecha;
    private Long caracteristicas;
    private Long marca_bus;
    private String status;
}
