## API REST para gerenciamento de sessões de votação

### Funcionalidades da API:

 - Cadastrar uma nova pauta
 - Abrir sessão de votação em uma pauta
 - Receber votos dos associados
 - Obter resultado da votação

### Tecnologias utilizadas
  - Java 8
  - Maven 3.6.3
  - Spring Boot (2.6.4), Spring Web
  - JUnit5, Mockito, Assertj
  - Swagger (3.0.0)
  - Docker (20.10.11), Docker Compose (1.27.4)
  - Banco de dados MongoDB
  - RabbitMQ

### Decisões técnicas

A aplicação foi dividida em três camadas, sendo elas: domínio, service e controller.

O domínio consiste das classes Pauta, Sessão e Voto. Cada uma dessas classes contém suas próprias regras de negócio e juntas definem uma collection não estrutura no mongodb.

**Exemplo de um documento da collection Pauta**
```
{
    _id: ObjectId("621c5ff552df3e5a49e3b51a"),
    titulo: 'Títutlo da pauta',
    sessaoVotacao: {
      tempoLimite: ISODate("2022-02-28T05:40:08.145Z"),
      votos: [
        {
          _id: '1',
          idAssociado: '1',
          cpfAssociado: '31209995042',
          voto: 'SIM'
        }
      ]
    },
    status: 'ENCERRADA',
}
```

Uma Pauta pode ter os seguintes status:
 - NAOINICIADA: Indica que essa pauta ainda não foi aberta para votação
 - ABERTA: Indica que a pauta foi aberta para votação
 - ENCERRADA: Indica que a sessão de votação para a pauta foi encerrada

Para encerrar uma pauta, foi definido um agendador de tarefas (schedule) que a cada segundo busca por todas as pautas abertas e verifica se o tempo limite de votação foi atingido. Quando encontrada, essa pauta é então marcado como ENCERRADA e o resultado da votação é contabilizado e enviado para a fila de mensagens pelo RabbitMQ.


### Executando o projeto

#### Serviços
- Servidor de mensageria RabbitMq
- Mongodb
- Pauta-API

#### Execução dos testes

```
mvn test -P dev
```

#### Execução da aplicação
```
docker-compose build
docker-compose up
```

### Endereço da API

http://localhost:8080/

### Documentação no Swagger

http://localhost:8080/swagger-ui/index.html

### RabbitMQ UI

http://localhost:15672/#/


