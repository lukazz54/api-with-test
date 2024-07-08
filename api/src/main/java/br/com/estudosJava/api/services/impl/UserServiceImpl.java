package br.com.estudosJava.api.services.impl;

import br.com.estudosJava.api.domain.User;
import br.com.estudosJava.api.domain.dto.UserDTO;
import br.com.estudosJava.api.repositories.UserRepository;
import br.com.estudosJava.api.services.UserService;
import br.com.estudosJava.api.services.exceptions.DataIntegratyViolationException;
import br.com.estudosJava.api.services.exceptions.ObjectNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;


    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Usuario não encontrado!"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO dto) {
        findByEmail(dto);
        return userRepository.save(mapper.map(dto, User.class));
    }

    private void findByEmail(UserDTO obj) {
        Optional<User> user = userRepository.findByEmail(obj.getEmail());

        if(user.isPresent() && user.get().getId().equals(obj.getId()))
            throw new DataIntegratyViolationException("Email já cadastrado no sistema!");
    }

    @Override
    public User update(UserDTO dto) {
        findByEmail(dto);
        return userRepository.save(mapper.map(dto, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }



}
