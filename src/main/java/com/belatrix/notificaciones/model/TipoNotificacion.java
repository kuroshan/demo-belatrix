package com.belatrix.notificaciones.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipo_notificacion")
public class TipoNotificacion {

    @Id
    @Size(max = 10)
    private String codigo;

    private int tiempoMaxVigencia;

}
