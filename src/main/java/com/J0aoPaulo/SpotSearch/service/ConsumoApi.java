package com.J0aoPaulo.SpotSearch.service;

import com.J0aoPaulo.SpotSearch.model.record.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ConsumoApi {

    private final ConverteDados dadosMapeados = new ConverteDados();
    private final String BASE_URL = "https://api.spotify.com/v1/artists/";

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

    public List<DadosArtista> getArtistBase(String artistName) {
        String BASE_URL_ID = "https://api.spotify.com/v1/search?q=";
        String URL_FILTER = "&type=artist&market=US&limit=1";
        Artistas dadosArtistaId = dadosMapeados.obterDados
                (apiResquest(BASE_URL_ID + artistName.replace(" ", "+")
                        + URL_FILTER), Artistas.class);
        return dadosArtistaId.artists().items();
    }

    public List<DadosTopMusicas> getArtistTopTrack(String artistName) {
        List<DadosArtista> artistBase = getArtistBase(artistName);
        List<DadosTopMusicas> artistTopTrack = new ArrayList<>();
        artistTopTrack.add(dadosMapeados.obterDados
                (apiResquest(BASE_URL + artistBase.getFirst().id()
                        + "/top-tracks"), DadosTopMusicas.class));
        return artistTopTrack;
    }

    public List<Albuns> getAlbuns(String artistName) {
        List<DadosArtista> dadosArtistas = getArtistBase(artistName);
        List<Albuns> albuns = new ArrayList<>();
        albuns.add(dadosMapeados.obterDados(apiResquest(BASE_URL + dadosArtistas.getFirst().id()
                + "/albums"), Albuns.class));
        return albuns;
    }

    public List<Musicas> getMusicas(String artistName) {
        List<Albuns> albuns = getAlbuns(artistName);
        List<Musicas> musicaDados = new ArrayList<>();
        List<String> albunsIds = albuns.stream()
                .flatMap(album -> album.albunsInfo().stream())
                .map(AlbumDados::albumId)
                .toList();
        for (String idAlbum : albunsIds) {
            musicaDados.add(dadosMapeados.obterDados(
                    apiResquest("https://api.spotify.com/v1/albums/" + idAlbum
                            + "/tracks?limit=50"), Musicas.class));
        }
        return musicaDados;
    }
}
