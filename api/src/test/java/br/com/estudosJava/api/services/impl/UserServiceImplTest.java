package br.com.estudosJava.api.services.impl;

import br.com.estudosJava.api.domain.User;
import br.com.estudosJava.api.domain.dto.UserDTO;
import br.com.estudosJava.api.repositories.UserRepository;
import br.com.estudosJava.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID      = 1;
    public static final String NAME     = "Lucas";
    public static final String EMAIL    = "lucas@email";
    public static final String PASSWORD = "123";

    @InjectMocks //Criar uma instancia de ServiceImpl, porem as demais instancias ele vai mockar
    private UserServiceImpl service;

    @Mock
    private UserRepository repository;

    @Mock
    private ModelMapper mapper;

    private User user;
    private UserDTO userDTO;
    private Optional<User> optionalUser;

    @BeforeEach //Antes de tudo faça o seguinte
    void setUp() {
        // O this faz referencia a classe que estamos testando
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        //Quanto o findById for chamado retorna o valor do Optional User que foi mockado
        when(repository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        //Assegurar que sempre vai ser retornado um tipo User
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Usuario não encontrado!"));

        try {
            service.findById(ID);
        }catch(Exception e ) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Usuario não encontrado!", e.getMessage());
        }
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}