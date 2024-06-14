package com.J0aoPaulo.SpotSearch.ui;

import com.J0aoPaulo.SpotSearch.service.ConsumoApi;

import java.util.Scanner;

public class UserInterface {

    private final Scanner input = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();

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
                consumoApi.getArtistTopTrack(nomeArtista);
                break;
            default:
                System.out.println("Opção inválida, digite novamente");
                break;
        }
    }
}