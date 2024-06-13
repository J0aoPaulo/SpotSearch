package com.J0aoPaulo.SpotSearch.ui;

import com.J0aoPaulo.SpotSearch.service.ConsumoApi;

import java.util.Scanner;

public class UserInterface {

    private final Scanner input = new Scanner(System.in);
    private final ConsumoApi consumoApi = new ConsumoApi();

    public void getDados() {
        System.out.print("Digite o artista que deseja procurar: ");
        String nomeArtista = input.nextLine();
        String artistId = consumoApi.getArtistId(nomeArtista);
        System.out.println(artistId);
    }
}
