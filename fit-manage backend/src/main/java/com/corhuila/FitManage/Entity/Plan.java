package com.corhuila.FitManage.Entity;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Entity
@Table(name = "planes")
@NoArgsConstructor
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombrePlan;
    private Double precio;
    private String descripcion;
    private String tipo;

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<Membresia> membresias;
    public Plan(String nombrePlan, Double precio, String descripcion, String tipo) {
        this.nombrePlan = nombrePlan;
        this.precio = precio;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }
}
