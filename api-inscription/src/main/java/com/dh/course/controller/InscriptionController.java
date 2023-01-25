package com.dh.course.controller;

import com.dh.course.service.InscriptionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/api/v1/inscription")
public class InscriptionController {

    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void create(@RequestBody InscriptionCreationRequest request) {
        inscriptionService.create(request.studentId, request.courseId, request.tuitionAmount, request.startInscription, request.endInscription);
    }


    @Getter
    @Setter
    public static class InscriptionCreationRequest {
        private Long studentId;
        private Long courseId;
        private BigDecimal tuitionAmount;
        private LocalDate startInscription;
        private LocalDate endInscription;
    }
}
