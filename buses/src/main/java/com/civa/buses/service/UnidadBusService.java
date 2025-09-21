package com.civa.buses.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.civa.buses.dtos.UnidadBusInputDTO;

import com.civa.buses.model.ComercialBus;
import com.civa.buses.model.ModeloBus;
import com.civa.buses.model.UnidadBus;
import com.civa.buses.repository.ComercialBusRepository;
import com.civa.buses.repository.ModeloBusRepository;
import com.civa.buses.repository.UnidadBusRepository;

@Service
public class UnidadBusService {
    @Autowired
    private UnidadBusRepository unidadBusRepository;

    @Autowired
    private ModeloBusRepository modeloBusRepository;

    @Autowired
    private ComercialBusRepository comercialBusRepository;

    public UnidadBus save (UnidadBusInputDTO entity){
        UnidadBus bus = new UnidadBus();

        bus.setNumero(entity.getNumero_bus());
        bus.setPlaca(entity.getPlaca());
        bus.setStatus(entity.getStatus()); 

        ComercialBus comercial = comercialBusRepository.findById(entity.getCaracteristicas())
                .orElseThrow(() -> new RuntimeException("ComercialBus no encontrado"));
        ModeloBus modelo = modeloBusRepository.findById(entity.getMarca_bus())
                .orElseThrow(() -> new RuntimeException("ModeloBus no encontrado"));

        bus.setComercialDatos(comercial);
        bus.setModeloBus(modelo);

        return unidadBusRepository.save(bus);
    }


    public List<UnidadBus> findAll() {
        return unidadBusRepository.findAll(Sort.by("numero").ascending());
    }

    public Optional<UnidadBus> findById (Long id){
        return unidadBusRepository.findById(id);
    }

}
