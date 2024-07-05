package br.com.estudosJava.api.services;

import br.com.estudosJava.api.domain.User;

public interface UserService {

    User findById(Integer id);
}
