package com.corhuila.FitManage.IService;

import com.corhuila.FitManage.Entity.Membresia;
import java.util.List;
import java.util.Optional;

public interface IMembresiaService {

    List<Membresia> findAll(); // Obtener todas las membresías

    Optional<Membresia> findById(Long id); // Buscar membresía por ID

    Membresia save(Membresia membresia); // Guardar o actualizar una membresía

    void deleteById(Long id); // Eliminar membresía por ID

    List<Membresia> findByClienteId(Long clienteId); // Buscar membresías por ID de cliente
}
