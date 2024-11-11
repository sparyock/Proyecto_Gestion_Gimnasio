package com.corhuila.FitManage.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Date;
@Data
@Entity
@Table(name = "facturas")
public class Factura {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long facturaId;

        @ManyToOne
        @JoinColumn(name = "cliente_id", nullable = false)
        private Cliente cliente;

        private Date fechaEmision;
        private Double montoTotal;
        private String metodoPago;
        private String estado;

}
