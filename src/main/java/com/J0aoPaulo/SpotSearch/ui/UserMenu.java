package com.J0aoPaulo.SpotSearch.ui;

import com.J0aoPaulo.SpotSearch.model.Album;
import com.J0aoPaulo.SpotSearch.model.Artista;
import com.J0aoPaulo.SpotSearch.model.Musica;
import com.J0aoPaulo.SpotSearch.model.TopMusica;
import com.J0aoPaulo.SpotSearch.model.record.Albuns;
import com.J0aoPaulo.SpotSearch.model.record.DadosTopMusicas;
import com.J0aoPaulo.SpotSearch.model.record.Musicas;
import com.J0aoPaulo.SpotSearch.repository.ArtistaRepository;
import com.J0aoPaulo.SpotSearch.service.ConsumoApi;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserMenu {

    private final Scanner input = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ArtistaRepository repository;

    public UserMenu(ArtistaRepository artistaRepository) {
        this.repository = artistaRepository;
    }

    private enum OpcaoMenu {
        SAIR, LISTAR_ALBUNS, LISTAR_MUSICAS
    }

    public void menu(String nomeArtista) {
        var menu = """
                    1 - Listar álbuns do artista
                    2 - Listar músicas do artista
                    0 - Sair                                \s
                   \s""";

        while (true) {
            System.out.println(menu);
            System.out.print("Digite a opção que deseja: ");
            int opcaoEscolhida = input.nextInt();
            input.nextLine();

            if (opcaoEscolhida < 0 || opcaoEscolhida >= OpcaoMenu.values().length) {
                System.out.println("Opção inválida, digite novamente");
                continue;
            }
            OpcaoMenu opcao = OpcaoMenu.values()[opcaoEscolhida];

            if (opcao == OpcaoMenu.SAIR) {
                break;
            }
            realizarAcao(opcao, nomeArtista);
        }
    }

    private void putDadosTopMusicas(Artista artista, String nomeArtista) {
        List<DadosTopMusicas> dadosTopMusicas = consumoApi.getArtistTopTrack(nomeArtista);
        List<TopMusica> topMusicas = dadosTopMusicas.stream()
                .flatMap(m -> m.nomesTopMusicas().stream())
                .map(TopMusica::new)
                .toList();
        artista.setTopMusicas(topMusicas);
    }

    private void putDadosAlbuns(Artista artista, String nomeArtista) {
        List<Albuns> albuns = consumoApi.getAlbuns(nomeArtista);
        List<Album> albumDados = albuns.stream()
                .flatMap(t -> t.albunsInfo().stream())
                .map(Album::new)
                .toList();
        artista.setAlbums(albumDados);
    }

    private void putDadosMusicas(Artista artista, String nomeArtista) {
        List<Musicas> musicasDados = consumoApi.getMusicas(nomeArtista);
        List<Musica> musicas = musicasDados.stream()
                .flatMap(d -> d.musicas().stream())
                .map(Musica::new)
                .toList();
        artista.setMusicas(musicas);
    }

    public void getDadosArtista(String nomeArtista) {
        final String artistNameApi = consumoApi.getArtistBase(nomeArtista).getFirst().nome();
        Artista artista = new Artista(artistNameApi);

        putDadosTopMusicas(artista, artistNameApi);
        putDadosAlbuns(artista, artistNameApi);
        putDadosMusicas(artista, artistNameApi);

        repository.save(artista);
    }

    private void realizarAcao(OpcaoMenu opcao, String nomeArtista) {
        switch (opcao) {
            case LISTAR_ALBUNS:
                Optional<List <Album>> albuns = repository.findAllAlbumsByArtistName(nomeArtista);
                albuns.ifPresentOrElse(
                        values -> values.forEach(a -> System.out.println(a.getNomeAlbum())),
                        () -> System.out.println("Nenhum album encontrado para esse artista")
                );
                break;
            case LISTAR_MUSICAS:
                Optional<List<Musica>> musicas = repository.findAllTracksByArtistName
                        (nomeArtista, PageRequest.of(0 ,20));
                musicas.ifPresentOrElse(
                        values -> values.forEach(m -> System.out.println(m.getNome())),
                        () -> System.out.println("Nenhuma música encontrada para esse artista")
                );
                break;
            case SAIR:
                break;
            default:
                System.out.println("Opção inválida, digite novamente");
                break;
        }
    }
}