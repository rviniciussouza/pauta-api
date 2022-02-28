package com.example.pautaapi.repository;

import java.util.List;

import com.example.pautaapi.domain.Pauta;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PautaRepository extends MongoRepository<Pauta, String>{

    @Query("{ 'status': ?0}")
    List<Pauta> findAllByStatus(String status);    
}
