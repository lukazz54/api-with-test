package br.com.estudosJava.api.services.impl;

import br.com.estudosJava.api.config.ModelMapperConfig;
import br.com.estudosJava.api.domain.User;
import br.com.estudosJava.api.repositories.UserRepository;
import br.com.estudosJava.api.services.UserService;
import br.com.estudosJava.api.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;


    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado!"));
    }
}
