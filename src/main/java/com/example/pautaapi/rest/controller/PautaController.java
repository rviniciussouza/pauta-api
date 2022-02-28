package com.example.pautaapi.rest.controller;

import javax.validation.Valid;

import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.domain.Voto;
import com.example.pautaapi.rest.request.PautaRequest;
import com.example.pautaapi.rest.request.SessaoRequest;
import com.example.pautaapi.rest.request.VotoRequest;
import com.example.pautaapi.rest.response.PautaResponse;
import com.example.pautaapi.rest.response.ResultadoResponse;
import com.example.pautaapi.service.PautaService;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/v1/pautas")
public class PautaController {

    private final Logger logger = LoggerFactory.getLogger(PautaController.class);

    @Autowired
    private PautaService service;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation(value = "Cria uma nova pauta", response = PautaResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Pauta criada com sucesso")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PautaResponse criarPauta(@RequestBody @Valid PautaRequest pautaRequest) {
        logger.info("Tentativa de criação da pauta {}", pautaRequest);
        Pauta pauta = modelMapper.map(pautaRequest, Pauta.class);
        return modelMapper.map(service.salvar(pauta), PautaResponse.class);
    }

    @ApiOperation(value = "Abre sessão de votação", response = PautaResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sessão de votação aberta com sucesso"),
        @ApiResponse(code = 404, message = "A pauta que você está tentando acessar não foi encontrada"),
        @ApiResponse(code = 409, message = "A sessão de votação para a pauta requisitada já foi aberta anteriormente"),
    })
    @PostMapping("/{idPauta}/abrir")
    @ResponseStatus(HttpStatus.OK)
    public PautaResponse abrirSessaoVotacao(
        @PathVariable @ApiParam(name = "Identificador da pauta", required = true) String idPauta,
        @RequestBody SessaoRequest sessaoRequest)
    {
        logger.info("Tentativa de abertura de sessão para a pauta {}", idPauta);
        Pauta pauta = service.abrirSessao(idPauta, sessaoRequest.getMinutos());
        return modelMapper.map(pauta, PautaResponse.class);
    }

    @ApiOperation(value = "Adiciona um novo voto", response = PautaResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Voto adicionado com sucesso"),
        @ApiResponse(code = 404, message = "A pauta que você está tentando acessar não foi encontrada"),
        @ApiResponse(code = 409, message = "A pauta requisitada não está aberta para votação")
    })
    @PostMapping("/{idPauta}/votar")
    @ResponseStatus(HttpStatus.OK)
    public PautaResponse votar(
        @PathVariable String idPauta,
        @RequestBody @Valid VotoRequest votoRequest)
    {
        logger.info("Tentativa de voto {} para a pauta {} ", votoRequest, idPauta);
        Voto voto = modelMapper.map(votoRequest, Voto.class);
        Pauta pauta = service.adicionarVoto(idPauta, voto);
        return modelMapper.map(pauta, PautaResponse.class);
    }

    @ApiOperation(value = "Recupera o resultado da sessão de votação", response = PautaResponse.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Resultado obtido com sucesso"),
        @ApiResponse(code = 404, message = "A pauta que você está tentando acessar não foi encontrada"),
        @ApiResponse(code = 409, message = "A pauta requisitada não possui uma sessão de votação")
    })
    @GetMapping("/{idPauta}/resultado")
    @ResponseStatus(HttpStatus.OK)
    public ResultadoResponse votar(@PathVariable String idPauta) {
        logger.info("Obtendo resultado para a pauta {}", idPauta);
        return service.obterResultadoVotacao(idPauta);
    }
}
