package com.corhuila.FitManage.IService;

import com.corhuila.FitManage.Entity.Plan;

import java.util.List;
import java.util.Optional;

public interface IPlanService {

    List<Plan> findAll(); // Obtener todos los planes

    Optional<Plan> findById(Long id); // Buscar plan por ID

    Plan save(Plan plan); // Guardar o actualizar un plan

    void deleteById(Long id); // Eliminar plan por ID

}
