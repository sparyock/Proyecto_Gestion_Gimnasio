package com.corhuila.FitManage.IRepository;

import com.corhuila.FitManage.Entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    // Método para encontrar facturas por cliente
    List<Factura> findByClienteId(Long clienteId);
}
