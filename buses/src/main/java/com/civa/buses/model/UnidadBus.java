package com.civa.buses.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bus")
public class UnidadBus {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_bus")
    private Long id;
    private Long numero;
    private String placa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime fecha;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name="comercial_id",referencedColumnName = "id_categoria")
    private ComercialBus comercialDatos;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "modelo_id",referencedColumnName = "id_modelo")
    private ModeloBus modeloBus;
    private String status;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
    
}
