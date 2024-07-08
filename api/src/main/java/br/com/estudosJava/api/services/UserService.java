package br.com.estudosJava.api.services;

import br.com.estudosJava.api.domain.User;
import br.com.estudosJava.api.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User create(UserDTO dto);
    User update(UserDTO dto);
    void delete(Integer id);
}
