# SpotSearch

<p align=center>
<img src="https://github.com/J0aoPaulo/SpotSearch/assets/98539735/5252de87-4cd0-442f-9516-a6f25f4bde48">
</p>

## Configuração do projeto

### Configuração da API
1. Acesse a [documentação da API do Spotify](https://developer.spotify.com/documentation/web-api/tutorials/getting-started).
2. Siga o primeiro passo e obtenha as credenciais `Client ID` e `Client Secret`.
3. Faça uma requisição POST para a endpoint abaixo, que retorna o token de acesso da API.
4. O token de acesso tem a duração de 60 minutos.
```bash
curl -X POST "https://accounts.spotify.com/api/token" \
   -H "Content-Type: application/x-www-form-urlencoded" \
   -d "grant_type=client_credentials&client_id=your-client-id&client_secret=your-client-secret"
```
5. Agora, com o token de acesso em mãos, sinta-se livre para fazer requisições de dados de um artista, álbum ou música. 
Consulte esta [documentação](https://developer.spotify.com/documentation/web-api/reference/search) para obter mais informações.
#### Atenção:
- Esses passos assumem que você tenha uma conta no Spotify, seja ela gratuita ou paga.
- A chamada da API é feita utilizando cURL. A instalação pode ser feita através do 
[site oficial](https://curl.se/download.html) ou de qualquer gerenciador de pacotes que selecione.
- Recomenda-se inserir o token de acesso nas variáveis de ambiente.

### Configuração do banco de dados
1. Clone o repositório e mude para a sua pasta raiz.
```
git clone https://github.com/J0aoPaulo/SpotSearch
cd spotsearch
```
2. Configure o banco de dados PostgreSQL com os seguintes comandos abaixo:
```properties
spring.application.name=SpotSearch

spring.datasource.url=jdbc:postgresql://DB_USERNAME
spring.datasource.username=${DB_POSTGRE_USERNAME}
spring.datasource.password=${DB_POSTGRE_PASSWORD}

spring.jpa.hibernate.ddl-auto=create
```
3. Após o projeto ser executado pela primeira vez e as tabelas forem criadas
mude o atributo de `spring.jpa.hibernate.ddl-auto` de `create `para `update`.

4. Configure as variáveis de ambiente para as credenciais do banco de dados.
```bash
export DB_USERNAME=your-db-username
export DB_PASSWORD=your-db-password
```

## Sobre o projeto
Um buscador que retorna dados sobre artistas, álbuns e músicas da API Web do Spotify. O principal objetivo do projeto foi
o estudo e prática de:
- Utilização do Hibernate + JPA como ORM do projeto.
- Funções lambda para manipulação de dados.
- Mapeamento de classes, por meio de anotações, para banco de dados relacional.
- Desserialização de dados utilizando Jackson e Records.
- Consultas no banco de dados PostgreSQL com JPQL, Named Queries e Queries nativas.
- Manipulação de JSON com diferentes tipos de estruturas. 

## Funcionalidades do projeto
- [x] Obter uma lista de álbuns de um artista.
- [x] Obter uma lista de músicas de um artista.
- [x] Obter as músicas mais populares de um artista.
- [x] Obter álbuns por tipo (Single, Album) específico.
- [x] Obter álbuns de acordo com o número de faixas em ordem decrescente.
- [x] Obter lista de artistas e álbuns.
