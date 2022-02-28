package com.example.pautaapi.schedule;

import java.util.List;

import com.example.pautaapi.constants.RabbitMQ;
import com.example.pautaapi.constants.StatusPauta;
import com.example.pautaapi.domain.Pauta;
import com.example.pautaapi.rest.response.ResultadoResponse;
import com.example.pautaapi.service.PautaService;
import com.example.pautaapi.service.RabbitMQService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class VerificadorDeSessoes {
    
    private static final Logger logger = LoggerFactory.getLogger(VerificadorDeSessoes.class);
    @Autowired
    private RabbitMQService rabbitMQService;
    @Autowired
    private PautaService pautaService;

    @Scheduled(fixedDelay = 3000)
    public void encerraSessoesRecemFinalizadas() {
        List<Pauta> pautasAbertas = pautaService.getPautasByStatus(StatusPauta.ABERTA);
        logger.info("{} pautas abertas encontradas", pautasAbertas.size());
        pautasAbertas.stream()
            .filter(pauta -> pauta.getSessaoVotacao().sessaoFinalizada())
            .map(pauta -> {
                logger.info("Encerrando sessão de votação pauta {}", pauta);
                return pautaService.encerrarSessao(pauta.getId());
            }).forEach(
                pautaEncerrada -> {
                    logger.info("Gerando resultado da votação para a pauta {}", pautaEncerrada);
                    ResultadoResponse resulado = pautaService.obterResultadoVotacao(pautaEncerrada.getId());
                    logger.info("Publicando resultado da votação {} na fila {}", resulado, RabbitMQ.QUEUE_PAUTA);
                    rabbitMQService.sendMessage(RabbitMQ.QUEUE_PAUTA, resulado);
                }
            );
    }
}
