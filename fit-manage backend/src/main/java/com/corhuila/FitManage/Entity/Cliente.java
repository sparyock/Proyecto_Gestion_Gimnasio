package com.corhuila.FitManage.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;
    private String telefono;
    private String direccion;
    @Column(name ="documento",unique = true)
    private String documento;
    private Date fechaNacimiento;

    private String email;

    @Column(name ="password")
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Membresia> membresias;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Factura> facturas;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Notificacion> notificaciones;

    // Getters and setters
}
