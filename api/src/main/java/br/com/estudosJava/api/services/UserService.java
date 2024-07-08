package br.com.estudosJava.api.services;

import br.com.estudosJava.api.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
}
