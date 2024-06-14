package com.J0aoPaulo.SpotSearch.service;

import com.J0aoPaulo.SpotSearch.model.DadosMusicaArtista;
import com.J0aoPaulo.SpotSearch.model.Artistas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsumoApi {

    private final ConverteDados dadosMapeados = new ConverteDados();

    String acessToken = System.getenv("ACESS_TOKEN");
    public String apiResquest(String endereco) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .header("Authorization", "Bearer " + acessToken)
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response.statusCode() == 401) {
            throw new RuntimeException("Código de acesso expirado.");
        }
        return response.body();
    }

    public String getArtistId(String artistName) {
        String BASE_URL = "https://api.spotify.com/v1/search?q=";
        String URL_FILTER = "&type=artist&market=US&limit=1";
        Artistas dadosArtistaId = dadosMapeados.obterDados
                (apiResquest(BASE_URL + artistName.replace(" ", "+")
                        + URL_FILTER), Artistas.class);
        return dadosArtistaId.artists().items().getFirst().id();
    }

    public void getArtistTopTrack(String artistName) {
        String idArtist = getArtistId(artistName);
        DadosMusicaArtista artistTopTrack = dadosMapeados.obterDados
                (apiResquest("https://api.spotify.com/v1/artists/" + idArtist + "/top-tracks")
                        , DadosMusicaArtista.class);
        artistTopTrack.tracks().forEach(t -> System.out.println(t.name()));
    }
}
