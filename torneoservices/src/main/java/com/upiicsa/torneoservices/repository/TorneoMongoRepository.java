package com.upiicsa.torneoservices.repository;

import java.util.Optional;

import com.upiicsa.torneoservices.model.TorneoDocument;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TorneoMongoRepository extends CrudRepository<TorneoDocument, String>{

    Optional<TorneoDocument> findByCorreo(String Correo);
}