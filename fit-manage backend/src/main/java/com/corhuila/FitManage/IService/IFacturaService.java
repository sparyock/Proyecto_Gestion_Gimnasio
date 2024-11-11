package com.corhuila.FitManage.IService;

import com.corhuila.FitManage.Entity.Factura;
import java.util.List;
import java.util.Optional;

public interface IFacturaService {

    List<Factura> findAll(); // Obtener todas las facturas

    Optional<Factura> findById(Long id); // Buscar factura por ID

    Factura save(Factura factura); // Guardar o actualizar una factura

    void deleteById(Long id); // Eliminar factura por ID

    List<Factura> findByClienteId(Long clienteId); // Buscar facturas por ID de cliente

}
