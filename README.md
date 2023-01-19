# Julgamento Eletrônico

<p align="center">🚀 Desafio proposto pela empresa SouthSystem para simulação de um julgamento eletrônico</p>

Tecnologias usadas:

Kafka

O Apache Kafka é uma plataforma distribuída de transmissão de dados que é capaz de publicar, subscrever, armazenar e
processar fluxos de registro em tempo real. E este foi escolhido para informa aos associados resultados de julgamento de
diversas pautas.

Swagger

Aplicação open source que auxilia desenvolvedores nos processos de definir, criar, documentar e consumir APIs REST.

Spring-cloud/Openfeign

Me auxiliou de forma prática fazer integração com servicos externos a aplicação e muito mais.

MongoDB - Banco de Dados

O MongoDB é orientado a documentos, ou seja, os dados são armazenados como documentos, ao contrário de bancos de dados
de modelo relacional, onde trabalhamos com registros em linhas e colunas. Os documentos podem ser descritos como dados
no formato de chave-valor, no caso, utilizando o formato JSON (JavaScript Object Notation). Este foi escolhido para este
desafio pela sua praticidade, agilidade e ganho de tempo no desenvolvimento de persistencia de dados em nossa base.

Lombok

O Lombok é uma biblioteca Java focada em produtividade e redução de código boilerplate que, por meio de anotações
adicionadas ao nosso código, ensinamos o compilador (maven ou gradle) durante o processo de compilação a criar código
Java.

# Construção e inicialização do projeto.

- mvn install: para download das dependencias no repositório maven.

- docker compose up: (na raiz do projeto) para rodar os containers docker.

- mvnw spring-boot:run: para startar o servidor da aplicação.

Links

- Swagger: http://localhost:8080/api/swagger-ui/index.html
- Postamn: https://www.getpostman.com/collections/57144039b3f1518fd03b



## Fluxo de sucesso

1 * Criação de usuário

curl -X POST "http://localhost:8080/api/usuario" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"cpf\": \"03782383180\", \"nome\": \"Alex Dias da Cruz\"}"

2 * Criação de uma pauta

curl -X POST "http://localhost:8080/api/pauta" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"assunto\": \"Pauta para descobrir junto a todos colaboradores se é necessário diminuirmos nossos salarios para manter empresa ativa.\", \"tema\": \"Pauta para diminuir salarios dos colaboradores\"}"

3 * Criar uma sessão de julgamento com id da pauta criada no passo *2

curl -X POST "http://localhost:8080/api/sessao-julgamento" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dataFim\": \"2023-01-22T20:40\", \"dataInicio\": \"2023-01-19T20:40\", \"idPauta\": \"63c9a2b530bf1e7803fd8f70\"}"

4 * Realizar voto na sessão de julgamento para alguma pauta

Primeiro voto
curl -X POST "http://localhost:8080/api/voto-participacao" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"idAssociado\": \"63c9a018264a90635fd75075\", \"idJulgamento\": \"63c9a38030bf1e7803fd8f71\", \"votoParticipacao\": \"S\"}"

Segundo voto

curl -X POST "http://localhost:8080/api/voto-participacao" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"idAssociado\": \"63c855e8fb70d8570ddaf725\", \"idJulgamento\": \"63c9a38030bf1e7803fd8f71\", \"votoParticipacao\": \"N\"}"


5* Encerrar e retornar sessão de julgamento daquela pauta

curl -X POST "http://localhost:8080/api/resultado-julgamento/encerrar-sessao/63c9a38030bf1e7803fd8f71" -H "accept: */*" -d ""

Contendo retorno com resultado.

{
"id": "63c9a83d2934d52aeb72eb19",
"idJulgamento": "63c9a38030bf1e7803fd8f71",
"resultadoVotacao": "EMPATE"
}


6 * Para obter resultado detalhado de julgamento

curl -X GET "http://localhost:8080/api/resultado-julgamento/obter-detalhes-resultado/63c9a83d2934d52aeb72eb19" -H "accept: */*"


{
"idJulgamento": "63c9a38030bf1e7803fd8f71",
"pautaRequestDto": {
"id": "63c9a2b530bf1e7803fd8f70",
"tema": "Pauta para diminuir salarios dos colaboradores",
"assunto": "Pauta para descobrir junto a todos colaboradores se é necessário diminuirmos nossos salarios para manter empresa ativa."
},
"votosContras": 1,
"votosFavoraveis": 1,
"resultadojulamento": "EMPATE",
"dataJulgamento": "2023-01-19 20:40"
}
