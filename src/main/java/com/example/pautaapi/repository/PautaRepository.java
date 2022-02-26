package com.example.pautaapi.repository;

import com.example.pautaapi.domain.Pauta;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaRepository extends MongoRepository<Pauta, String>{
    
}
