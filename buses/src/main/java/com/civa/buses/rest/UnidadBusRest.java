package com.civa.buses.rest;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.civa.buses.dtos.ComercialBusDTO;
import com.civa.buses.dtos.ModeloBusDTO;
import com.civa.buses.dtos.UnidadBusDTO;
import com.civa.buses.dtos.UnidadBusInputDTO;
import com.civa.buses.model.ComercialBus;
import com.civa.buses.model.ModeloBus;
import com.civa.buses.model.UnidadBus;
import com.civa.buses.service.UnidadBusService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/bus")
@CrossOrigin(origins = "http://localhost:5173")
public class UnidadBusRest {

    @Autowired
    private UnidadBusService unidadBusService;

    @PostMapping(consumes = "application/json")
    private ResponseEntity<Map<String,Object>> saveBus(@RequestBody UnidadBusInputDTO dto) {


        UnidadBus saved = unidadBusService.save(dto);
        Map<String, Object> response = new HashMap<>();
        response.put("id", saved.getId());
        response.put("numero_bus", saved.getNumero());
        response.put("placa", saved.getPlaca());
        response.put("status", saved.getStatus());
        response.put("fecha", 
            saved.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        );

        Map<String, Object> caracteristicas = new HashMap<>();
        caracteristicas.put("id", saved.getComercialDatos().getId());
        caracteristicas.put("branding", saved.getComercialDatos().getBranding());
        caracteristicas.put("asientos", saved.getComercialDatos().getAsientos());
        caracteristicas.put("detalles", saved.getComercialDatos().getDetalles());
        response.put("caracteristicas", caracteristicas);

        Map<String, Object> marcaBus = new HashMap<>();
        marcaBus.put("id", saved.getModeloBus().getId());
        marcaBus.put("fabricante", saved.getModeloBus().getFabricante());
        marcaBus.put("modelo", saved.getModeloBus().getModelo());
        response.put("marca_bus", marcaBus);

        return ResponseEntity.ok(response);

    }
    
    @GetMapping
    private ResponseEntity<List<UnidadBusDTO>> getAllUnidadBus () {
        List<UnidadBusDTO> buses = unidadBusService.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();

        return ResponseEntity.ok(buses);
    } 

    @GetMapping("/{id}")
    public ResponseEntity<UnidadBusDTO> getUnidadBusById(@PathVariable Long id) {
        Optional<UnidadBusDTO> unidadBusDTO = unidadBusService.findById(id).map(this::convertToDTO);
        return ResponseEntity.ok(unidadBusDTO.get());
    }
    
    
    private UnidadBusDTO convertToDTO(UnidadBus bus) {
        UnidadBusDTO dto = new UnidadBusDTO();
        dto.setId(bus.getId());
        dto.setNumero_bus(bus.getNumero());
        dto.setPlaca(bus.getPlaca());
        dto.setFecha(bus.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dto.setStatus(bus.getStatus());

        // Map ComercialBus
        ComercialBusDTO comercialDTO = new ComercialBusDTO();
        comercialDTO.setId(bus.getComercialDatos().getId());
        comercialDTO.setBranding(bus.getComercialDatos().getBranding());
        comercialDTO.setAsientos(bus.getComercialDatos().getAsientos());
        comercialDTO.setDetalles(bus.getComercialDatos().getDetalles());
        dto.setCaracteristicas(comercialDTO);

        // Map ModeloBus
        ModeloBusDTO modeloDTO = new ModeloBusDTO();
        modeloDTO.setId(bus.getModeloBus().getId());
        modeloDTO.setFabricante(bus.getModeloBus().getFabricante());
        modeloDTO.setModelo(bus.getModeloBus().getModelo());
        dto.setMarca_bus(modeloDTO);

        return dto;
    }
}
