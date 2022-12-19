package com.myCompagny.Apigestionregions;

import com.myCompagny.Apigestionregions.Modele.AppRole;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class ApigestionregionsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigestionregionsAppApplication.class, args);
	}

	// Encoder les mots de passes
	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}


	// Au démarrage de l'application, on va ajouter quelque users
	@Bean // pour que lamba execute
	CommandLineRunner start(AccountService accountService){ // notre interface Account en param
		// on retourne une expréssion lamba qui va exsécuter lors du démarrage
		return args -> {
			// Ajoutons deux Rôles USER, ADMIN
			accountService.addNewRole(new AppRole(null, "USER"));
			accountService.addNewRole((new AppRole(null, "ADMIN")));

			// Ajoutons des Utilisateurs
			accountService.addNewUser(new AppUser(null,"user1","123", new ArrayList<>()));
			accountService.addNewUser(new AppUser(null,"admin","123", new ArrayList<>()));

			// Affectons des rôles aux deux utilisateurs ajouter
			accountService.addRoleToUser("user1","USER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");
		};
	}
}
