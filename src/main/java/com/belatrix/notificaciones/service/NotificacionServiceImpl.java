package com.belatrix.notificaciones.service;

import com.belatrix.notificaciones.model.Notificacion;
import com.belatrix.notificaciones.model.TipoNotificacion;
import com.belatrix.notificaciones.payload.MensajeResponse;
import com.belatrix.notificaciones.payload.NotificacionRequest;
import com.belatrix.notificaciones.repository.NotificacionRepository;
import com.belatrix.notificaciones.repository.TipoNotificacionRepository;
import io.reactivex.Single;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.Optional;

@Service
public class NotificacionServiceImpl implements NotificacionService{ ;

    @Autowired
    private NotificacionRepository notificacionRepository;

    @Autowired
    private TipoNotificacionRepository tipoNotificacionRepository;

    @Override
    public Single<Long> registrar(NotificacionRequest request) {
        return Single.create(singleSubscriber -> {
            Notificacion n = new Notificacion();
            n.setNombreCliente(request.getNombreCliente());
            n.setOtpIdentificacion(request.getOtpIdentificacion());
            n.setCodigoOperacion(request.getCodigoOperacion());
            n.setFechaTransaccion(request.getFechaTransaccion());
            n.setCabecera(request.getCabecera());
            n.setFechaPago(request.getFechaPago());
            Optional<TipoNotificacion> tn = tipoNotificacionRepository.findById(request.getTipoNotificacion());
            n.setTipoNotificacion(tn.get());
            n.setTiempoMaxVigencia(tn.get().getTiempoMaxVigencia());
            n = notificacionRepository.save(n);
            singleSubscriber.onSuccess(n.getCodigo());
        });
    }

    @Override
    public Single<MensajeResponse> obtener(Long codigo) {
        return Single.create(singleSubscriber -> {
            Optional<Notificacion> n = notificacionRepository.findById(codigo);

            VelocityEngine ve = new VelocityEngine();
            ve.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
            ve.init();
            Template t = ve.getTemplate(n.get().getTipoNotificacion().getCodigo() + ".vm");
            VelocityContext vc = new VelocityContext();
            vc.put("cliente", n.get().getNombreCliente());
            vc.put("titulo", n.get().getCabecera());
            vc.put("otp", n.get().getOtpIdentificacion());

            StringWriter sw = new StringWriter();
            t.merge(vc, sw);

            MensajeResponse response = new MensajeResponse(sw.toString());
            singleSubscriber.onSuccess(response);
        });
    }

}
