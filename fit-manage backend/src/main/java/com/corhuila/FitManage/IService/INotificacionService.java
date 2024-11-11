package com.corhuila.FitManage.IService;

import com.corhuila.FitManage.Entity.Notificacion;
import java.util.List;
import java.util.Optional;

public interface INotificacionService {

    List<Notificacion> findAll();

    Optional<Notificacion> findById(Long id);

    Notificacion save(Notificacion notificacion);

    void deleteById(Long id);

    List<Notificacion> findByClienteId(Long clienteId);

    // Método para enviar notificación por correo electrónico
    void sendEmailNotification(String to, String subject, String message);
}