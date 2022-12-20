package com.myCompagny.Apigestionregions;

import com.myCompagny.Apigestionregions.Modele.AppRole;
import com.myCompagny.Apigestionregions.Modele.AppUser;
import com.myCompagny.Apigestionregions.Repository.AppRoleRepository;
import com.myCompagny.Apigestionregions.Repository.AppUserRepository;
import com.myCompagny.Apigestionregions.Service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@AllArgsConstructor
public class ApigestionregionsAppApplication {

	// Pour l'injection de notre Repository Role pour vérifier la table
	private AppRoleRepository appRoleRepository;

	// Pour l'injection de notre Repository AppUser pour vérifier la table
	private AppUserRepository appUserRepository;

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
			// Vérifier si la table Rôle est vide dans la base de donnée
			if (appRoleRepository.findAll().size() == 0){
				// Si c'est vide, on ajoute deux Rôles USER, ADMIN
				accountService.addNewRole(new AppRole(null, "USER"));
				accountService.addNewRole((new AppRole(null, "ADMIN")));
			}

			// Ajoutons un Admin à la fois User par défaut
			if (appUserRepository.findAll().size() == 0){
				// Ajoutons un Admin par defaut
				accountService.addNewUser(new AppUser(null,"admin","12345678", new ArrayList<>()));

				// Affectons des rôles aux deux à l'Admin ajouter
				accountService.addRoleToUser("admin", "USER");
				accountService.addRoleToUser("admin", "ADMIN");
			}

		};
	}
}
