package com.vetnova.notificaciones.controller;

import com.vetnova.notificaciones.model.Notificacion;
import com.vetnova.notificaciones.repository.NotificacionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionRepository notificacionRepository;

    // 1. POST: Crear/Enviar una notificación con validaciones
    @PostMapping
    public ResponseEntity<?> crearNotificacion(@Valid @RequestBody Notificacion notificacion, BindingResult resultado) {
        
        // Si hay errores de validación (campos vacíos, etc.), los atrapamos aquí
        if (resultado.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            resultado.getFieldErrors().forEach(error -> 
                errores.put(error.getField(), error.getDefaultMessage())
            );
            return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
        }

        // Si pasa las validaciones, guardamos en la base de datos de XAMPP
        Notificacion nuevaNotificacion = notificacionRepository.save(notificacion);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    // 2. GET: Listar todo el historial de notificaciones de VetNova
    @GetMapping
    public List<Notificacion> listarTodas() {
        return notificacionRepository.findAll();
    }
}