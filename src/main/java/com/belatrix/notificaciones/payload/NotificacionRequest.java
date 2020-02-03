package com.belatrix.notificaciones.payload;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotificacionRequest {

    private String tipoNotificacion;

    private String nombreCliente;

    private Long otpIdentificacion;

    private Long codigoOperacion;

    private Instant fechaTransaccion;

    private String cabecera;

    private Instant fechaPago;

}
