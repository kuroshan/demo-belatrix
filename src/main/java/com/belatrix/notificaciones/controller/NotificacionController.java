package com.belatrix.notificaciones.controller;

import com.belatrix.notificaciones.payload.BaseWebResponse;
import com.belatrix.notificaciones.payload.MensajeResponse;
import com.belatrix.notificaciones.payload.NotificacionRequest;
import com.belatrix.notificaciones.repository.NotificacionRepository;
import com.belatrix.notificaciones.service.NotificacionService;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private NotificacionService notificacionService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public Single<ResponseEntity<BaseWebResponse>> registrar(@RequestBody NotificacionRequest request) {
        return notificacionService.registrar(request)
                .subscribeOn(Schedulers.io())
                .map(id -> ResponseEntity.created(URI.create("/api/notificaciones/" + id)).body(BaseWebResponse.successWithData(id)));
    }

    @GetMapping("/mensaje/{codigo}")
    @PreAuthorize("hasRole('USER')")
    public Single<ResponseEntity<BaseWebResponse<MensajeResponse>>> obtenerMensaje(@PathVariable Long codigo) {
        return notificacionService.obtener(codigo)
                .subscribeOn(Schedulers.io())
                .map(mensajeResponse -> ResponseEntity.ok(BaseWebResponse.successWithData(mensajeResponse)));
    }

}
