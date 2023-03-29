package com.dh.student.event;


import com.dh.student.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Slf4j
public class FinalizarCursoEventConsumer {


    @RabbitListener(queues = RabbitMQConfig.QUEUE_CURSO_FINALIZADO)
    public void listen(FinalizarCursoEventConsumer.Data message){
       log.info("NOMBRE DE CURSO "+ message.nombreCurso);
        //procesamiento
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String nombreCurso;
        private int nota;
        private String saludo;
    }
}
