package br.com.estudosJava.api.config;

import br.com.estudosJava.api.domain.User;
import br.com.estudosJava.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local") // Anotação que separa as configuração em niveis, podendo ter configs locais e de prod separadas
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        User u1 = new User(null, "Lucas", "lucas@email", "123");
        User u2 = new User(null, "Barbara", "barbara@email", "123");

        //userRepository.saveAll(Arrays.asList(u1, u2));
        userRepository.saveAll(List.of(u1, u2));

    }
}
