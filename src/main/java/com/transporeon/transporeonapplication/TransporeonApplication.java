package com.transporeon.transporeonapplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransporeonApplication {

	//To-do:
	// fajniejsza walidacja
	// wymyslic zadania (np. dopisz usuwanie, edycje, dodawanie wielu miast na raz, a potem testy do tego)
	// podziel zadanka na integracyjne i mockito
	public static void main(String[] args) {
		SpringApplication.run(TransporeonApplication.class, args);
	}

}
