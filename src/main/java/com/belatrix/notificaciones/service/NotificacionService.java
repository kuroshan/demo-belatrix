package com.belatrix.notificaciones.service;

import com.belatrix.notificaciones.payload.MensajeResponse;
import com.belatrix.notificaciones.payload.NotificacionRequest;
import io.reactivex.Single;

public interface NotificacionService {

    Single<Long> registrar(NotificacionRequest request);

    Single<MensajeResponse> obtener(Long codigo);

}
