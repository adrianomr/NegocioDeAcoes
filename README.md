# NegocioDeAcoes

Implementação de um sistema de emissão e monitoramento de ações de empresas.

Foram implementados 4 serviços, disponibilizados através de Webservices REST.

O sistema foi desenvolvido em java com auxílio da plataforma Spring Boot. Como biblioteca de persistência, foi utilizado Spring Data JPA (que já vem com o hibernate). A escolha do Spring Data JPA deu-se pelo fato de ele trabalhar com implementação de interfaces, permitindo assim a reutilização do código para diferentes soluçoes de bibliotecas de persistência. Foi utilizado a biblioteca Spring WEB para suporte ao gerenciamento de uma plataforma WEB. E por fim, foi utilizada a biblioteca do PostgreSQL para fazer a integração com o banco de dados instalado na máquina.

Os fontes iniciais foram gerados através do Spring Initializr, sendo possível já selecionar as bibliotecas utilizadas através do site do Spring Initializr. Depois de selecionadas as bibliotecas, e nomeado o projeto, foi feito download dos fontes gerados pelo site.

Foi feito um backup do banco utilizado em desenvolvimento e anexado na pasta "/src/main/sql". As tabelas utilizadas na aplicação são: conta, monitoramento e transacoes.

Três dos serviços desenvolvidos foram para dar acesso a esses recursos mencionados. O último é um serviço de emissão de preços. Depois de inicializada a aplicação, é necessário fazer uma requisição GET para o serviço de emissão de preços, para iniciar a emissão. Após iniciada a emissão, o sistema busca no banco os lançamentos de monitoramento, e começa a enviar preços ao para o serviço de monitoramento. Este serviço reage aos preços emitidos conforme uma regra e decidi se deve comprar ou vender ações de uma determinada empresa. Em seguida, serão listados os serviços e os seus endpoints disponíveis:

# Emissor de Preços

localhost:8080/emissor-precos/iniciar

GET - inicia a emissão de preços

# Conta

localhost:8080/contas/

GET - Listagem de Contas

POST - Inclusão de Contas

localhost:8080/contas/{conta_id}

PUT - Alteração de Contas

DELETE - Remoção de Contas

# Monitoramento

localhost:8080/contas/{conta_id}/monitoramentos

GET - Listagem de Monitoramentos

POST - Inclusão de Monitoramentos

localhost:8080/contas/{conta_id}/monitoramentos/{transacoes_id}

PUT - Alteração de Monitoramentos

DELETE - Remoção de Monitoramentos

# Transações

localhost:8080/contas/{conta_id}/transacoes

GET - Listagem de Monitoramentos

POST - Inclusão de Monitoramentos

localhost:8080/contas/{conta_id}/transacoes/{transacoes_id}

PUT - Alteração de Monitoramentos

DELETE - Remoção de Monitoramentos

localhost:8080/contas/{conta_id}/monitoramentos/{monitoramento_id}/preco

POST - Método para receber preco de ações.
