package com.corhuila.FitManage.Service;

import com.corhuila.FitManage.Entity.Notificacion;
import com.corhuila.FitManage.IRepository.NotificacionRepository;
import com.corhuila.FitManage.IService.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionService implements INotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public List<Notificacion> findAll() {
        return notificacionRepository.findAll();
    }

    @Override
    public Optional<Notificacion> findById(Long id) {
        return notificacionRepository.findById(id);
    }

    @Override
    public Notificacion save(Notificacion notificacion) {
        return notificacionRepository.save(notificacion);
    }

    @Override
    public void deleteById(Long id) {
        notificacionRepository.deleteById(id);
    }

    @Override
    public List<Notificacion> findByClienteId(Long clienteId) {
        return notificacionRepository.findByClienteId(clienteId);
    }

    @Override
    public void sendEmailNotification(String to, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(to);
        email.setSubject(subject);
        email.setText(message);
        email.setFrom("jsnaranjo-2022a@corhuila.edu.co"); // Reemplazar con el correo configurado

        mailSender.send(email);
    }
}
