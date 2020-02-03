package com.belatrix.notificaciones.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table( name = "notificaciones")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @ManyToOne
    @JoinColumn(name = "tipo_notificacion")
    private TipoNotificacion tipoNotificacion;

    private String nombreCliente;

    private Long otpIdentificacion;

    private Long codigoOperacion;

    private Instant fechaTransaccion;

    private String cabecera;

    private Instant fechaPago;

    private int tiempoMaxVigencia;

}
