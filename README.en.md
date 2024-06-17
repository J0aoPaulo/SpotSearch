# SpotSearch

[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/J0aoPaulo/currency-converter/blob/main/README.pt-br.md)

<p align=center>
<img src="https://github.com/J0aoPaulo/SpotSearch/assets/98539735/5252de87-4cd0-442f-9516-a6f25f4bde48">
</p>

## Project configuration

### API Configuration

1.  Access[Spotify API documentation](https://developer.spotify.com/documentation/web-api/tutorials/getting-started).
2.  Follow the first step and obtain the credentials`Client ID`e`Client Secret`.
3.  Make a POST request to the endpoint below, which returns the API access token.
4.  The access token lasts for 60 minutes.

```bash
curl -X POST "https://accounts.spotify.com/api/token" \
   -H "Content-Type: application/x-www-form-urlencoded" \
   -d "grant_type=client_credentials&client_id=your-client-id&client_secret=your-client-secret"
```

5.  Now, with the access token in hand, feel free to make data requests for an artist, album or song. 
    See this[documentation](https://developer.spotify.com/documentation/web-api/reference/search)for more information.

#### Attention:

-   These steps assume you have a Spotify account, whether free or paid.
-   The API call is made using cURL. Installation can be done through the[official site](https://curl.se/download.html)or from any package manager you select.
-   It is recommended to insert the access token in the environment variables.

### Database configuration

1.  Clone the repository and move to your root folder.


    git clone https://github.com/J0aoPaulo/SpotSearch
    cd spotsearch

2.  Configure the PostgreSQL database with the following commands below:

```properties
spring.application.name=SpotSearch

spring.datasource.url=jdbc:postgresql://DB_USERNAME
spring.datasource.username=${DB_POSTGRE_USERNAME}
spring.datasource.password=${DB_POSTGRE_PASSWORD}

spring.jpa.hibernate.ddl-auto=create
```

3.  After the project is run for the first time and tables are created
    change the attribute of`spring.jpa.hibernate.ddl-auto`of`create `for`update`.

4.  Set the environment variables for the database credentials.

```bash
export DB_USERNAME=your-db-username
export DB_PASSWORD=your-db-password
```

## About the project

A search engine that returns data about artists, albums and songs from Spotify's Web API. The main objective of the project was
the study and practice of:

-   Use of Hibernate + JPA as the project's ORM.
-   Lambda functions for data manipulation.
-   Mapping classes, through annotations, to relational database.
-   Data deserialization using Jackson and Records.
-   Queries in the PostgreSQL database with JPQL, Named Queries and native Queries.
-   JSON manipulation with different types of structures.

## Project features

-   [x] Get a list of an artist's albums.
-   [x] Get a list of an artist's songs.
-   [x] Get an artist's most popular songs.
-   [x] Get albums by specific type (Single, Album).
-   [x] Get albums according to number of tracks in descending order.
-   [x] Get list of artists and albums.
