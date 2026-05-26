package com.vetnova.notificaciones.controller;

import com.vetnova.notificaciones.model.Notificacion;
import com.vetnova.notificaciones.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    // Ahora inyectamos el Servicio en lugar del Repositorio
    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // 1. POST: Crear una notificación usando el servicio
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.guardar(notificacion);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    // 2. GET: Listar todo el historial usando el servicio
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarTodas() {
        List<Notificacion> historial = notificacionService.listarTodas();
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }
}