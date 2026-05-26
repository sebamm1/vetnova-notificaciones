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

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Notificacion> crearNotificacion(@Valid @RequestBody Notificacion notificacion) {
        Notificacion nuevaNotificacion = notificacionService.guardar(notificacion);
        return new ResponseEntity<>(nuevaNotificacion, HttpStatus.CREATED);
    }

    // READ (Todos)
    @GetMapping
    public ResponseEntity<List<Notificacion>> listarTodas() {
        List<Notificacion> historial = notificacionService.listarTodas();
        return new ResponseEntity<>(historial, HttpStatus.OK);
    }

    // READ (Uno por ID)
    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> obtenerPorId(@PathVariable Long id) {
        return notificacionService.listarTodas().stream()
                .filter(n -> n.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Long id, @Valid @RequestBody Notificacion notificacion) {
        notificacion.setId(id);
        Notificacion actualizada = notificacionService.guardar(notificacion);
        return ResponseEntity.ok(actualizada);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}