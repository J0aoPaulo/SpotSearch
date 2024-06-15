package com.J0aoPaulo.SpotSearch.ui;

import com.J0aoPaulo.SpotSearch.model.Artista;
import com.J0aoPaulo.SpotSearch.model.Musica;
import com.J0aoPaulo.SpotSearch.model.record.DadosTopMusicas;
import com.J0aoPaulo.SpotSearch.repository.ArtistaRepository;
import com.J0aoPaulo.SpotSearch.service.ConsumoApi;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private final Scanner input = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();
    private final ArtistaRepository repository;

    public UserInterface(ArtistaRepository artistaRepository) {
        this.repository = artistaRepository;
    }

    private enum OpcaoMenu {
        SAIR, BUSCAR_TOP_20
    }

    public void menu() {
        var menu = """
                    1 - Buscar top 20 músicas
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

            System.out.print("Digite o nome do artista no qual deseja essas informações: ");
            String nomeArtista = input.nextLine();
            realizarAcao(opcao, nomeArtista);
        }
    }

    private void realizarAcao(OpcaoMenu opcao, String nomeArtista) {
        switch (opcao) {
            case BUSCAR_TOP_20:
                List<DadosTopMusicas> dadosTopMusicas = consumoApi.getArtistTopTrack(nomeArtista);
                List<Musica> topMusicas = dadosTopMusicas.stream()
                        .flatMap(m -> m.nomesTopMusicas().stream())
                        .map(t -> new Musica(t.name()))
                        .toList();
                Artista artista = new Artista(nomeArtista);
                artista.setTopMusicas(topMusicas);
                repository.save(artista);
                break;
            default:
                System.out.println("Opção inválida, digite novamente");
                break;
        }
    }
}