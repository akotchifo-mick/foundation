package recipe.diso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import recipe.diso.config.auth.AuthenticationService;
import recipe.diso.config.auth.RegisterRequest;
import recipe.diso.models.Label;

@SpringBootApplication
public class DisoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthenticationService service
	) {
		return args -> {
			var user1 = RegisterRequest.builder()
					.username("diso")
					.email("diso@mail.com")
					.password("password")
					.build();
			System.out.println("user1 token: " + service.register(user1).getAccessToken());

			var user2 = RegisterRequest.builder()
					.username("mick")
					.email("mikc@mail.com")
					.password("password")
					.build();
			System.out.println("User2 token: " + service.register(user2).getAccessToken());

			var label = Label.builder()
					.name("Desserts")
					.build();


		};
	}

}
