package com.dh.student.event;


import com.dh.student.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FinalizarCursoEventConsumer {


    @RabbitListener(queues = RabbitMQConfig.QUEUE_CURSO_FINALIZADO)
    public void listen(FinalizarCursoEventConsumer.Data message){
        System.out.print("NOMBRE DE CURSO "+ message.nombreCurso);
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
