package com.corhuila.FitManage.IRepository;

import com.corhuila.FitManage.Entity.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

    // Método para encontrar notificaciones por cliente
    List<Notificacion> findByClienteId(Long clienteId);
}
