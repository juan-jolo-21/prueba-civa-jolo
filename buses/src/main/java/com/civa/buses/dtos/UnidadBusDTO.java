package com.civa.buses.dtos;

import lombok.Data;

@Data
public class UnidadBusDTO {
    private Long id;
    private Long numero_bus;
    private String placa;
    private String fecha;
    private ComercialBusDTO caracteristicas;
    private ModeloBusDTO marca_bus;
    private String status;
}
