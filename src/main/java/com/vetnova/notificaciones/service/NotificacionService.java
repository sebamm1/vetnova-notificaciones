package com.vetnova.notificaciones.service;

import com.vetnova.notificaciones.model.Notificacion;
import com.vetnova.notificaciones.repository.NotificacionRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;

    // Inyección de dependencias por constructor
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    // Lógica para guardar
    public Notificacion guardar(Notificacion notificacion) {
        // Aquí podrías poner lógica de negocio en el futuro
        return notificacionRepository.save(notificacion);
    }

    // Lógica para listar
    public List<Notificacion> listarTodas() {
        return notificacionRepository.findAll();
    }
}