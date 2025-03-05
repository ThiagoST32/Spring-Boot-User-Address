# Spring-Boot-User-Address
Projeto feito com a intenção de se ter uma API que irá criar um usuario juntamente ao seu CEP, sendo ligado entre eles mesmo e usando como recurso uma API externa para a busca dos dados conforme o CEP apresentado pelo usuario.

# Dependencias usadas
 - Spring Data JPA 
 - H2 Database
 - Spring Web
 - OpenFeign
 - Jackson Databind
 - Validation

# Cadastro de Usuário e Endereço:
 - O projeto permite o cadastro de usuários, onde cada usuário pode ter um endereço associado.
 - O endereço é obtido através de um CEP fornecido pelo usuário, que é validado e completado com informações de uma API externa.

# Integração com API externa:
 - O projeto integra-se com uma API externa (como a ViaCEP) para buscar informações detalhadas do endereço a partir do CEP fornecido.
 - A chamada à API é feita de forma assíncrona para melhorar a performance e a responsividade da aplicação.

# Tratamento de Exceções: 
 - O projeto inclui um robusto tratamento de exceções para lidar com possíveis erros, como CEP inválido, falha na comunicação com a API externa, ou problemas de validação de dados.
 - Exceções personalizadas são utilizadas para fornecer mensagens de erro claras e específicas para o usuário.
 - 
# Validação de Dados:
 - O projeto implementa validações tanto no lado do cliente quanto no servidor para garantir que os dados fornecidos pelo usuário sejam corretos e completos.
 - Anotações do Bean Validation são utilizadas para validar campos como CEP, nome, e-mail, etc.

# Arquitetura e Organização do Código:
 - O código está organizado seguindo as boas práticas do Spring Boot, com separação clara entre camadas de controle, serviço e repositório.
 - O uso de DTOs (Data Transfer Objects) é empregado para transferir dados entre as camadas da aplicação, garantindo uma separação clara de responsabilidades.

# Documentação e Testes:
 - O projeto inclui documentação básica sobre como configurar e executar a aplicação.
 - Testes unitários e de integração são implementados para garantir a qualidade e a confiabilidade do código.
 
