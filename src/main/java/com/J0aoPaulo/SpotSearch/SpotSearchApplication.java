package com.J0aoPaulo.SpotSearch;

import com.J0aoPaulo.SpotSearch.repository.ArtistaRepository;
import com.J0aoPaulo.SpotSearch.ui.UserMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpotSearchApplication implements CommandLineRunner {

	@Autowired
	ArtistaRepository artistaRepository;

	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(SpotSearchApplication.class, args);
	}

	@Override
	public void run(String... Args) {
		UserMenu userInterface = new UserMenu(artistaRepository);
		System.out.print("Digite o nome do artista: ");
		String nomeArtista = input.nextLine();
		userInterface.getDadosArtista(nomeArtista);
		userInterface.menu(nomeArtista);
	}
}
