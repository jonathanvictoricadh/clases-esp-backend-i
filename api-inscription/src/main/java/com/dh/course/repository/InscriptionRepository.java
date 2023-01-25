package com.dh.course.repository;

import com.dh.course.model.Inscription;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends MongoRepository<Inscription,String> {
}
