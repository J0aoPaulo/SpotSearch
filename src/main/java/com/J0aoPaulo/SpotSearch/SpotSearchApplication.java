package com.J0aoPaulo.SpotSearch;

import com.J0aoPaulo.SpotSearch.ui.UserInterface;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotSearchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpotSearchApplication.class, args);
	}

	@Override
	public void run(String... Args) {
		UserInterface userInterface = new UserInterface();
		userInterface.getDados();
	}
}
