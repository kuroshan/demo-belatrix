package com.belatrix.notificaciones.repository;

import com.belatrix.notificaciones.model.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoNotificacionRepository extends JpaRepository<TipoNotificacion, String> {

}
