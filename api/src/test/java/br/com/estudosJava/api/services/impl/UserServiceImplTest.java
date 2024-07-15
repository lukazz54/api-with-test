package br.com.estudosJava.api.services.impl;

import br.com.estudosJava.api.domain.User;
import br.com.estudosJava.api.domain.dto.UserDTO;
import br.com.estudosJava.api.repositories.UserRepository;
import br.com.estudosJava.api.services.exceptions.DataIntegratyViolationException;
import br.com.estudosJava.api.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
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
    private UserRepository userRepository;

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
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = service.findById(ID);

        //Assegurar que sempre vai ser retornado um tipo User
        assertEquals(User.class, response.getClass());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(userRepository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Usuario não encontrado!"));

        try {
            service.findById(ID);
        }catch(Exception e ) {
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Usuario não encontrado!", e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(0).getClass());

        assertEquals(ID, response.get(0).getId());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = service.create(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenCreateThanReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("Email já cadastrado no sistema!", e.getMessage());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = service.update(userDTO);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(EMAIL, response.getEmail());
    }

    @Test
    void whenUpdateThanReturnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            service.create(userDTO);
        }catch (Exception e) {
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("Email já cadastrado no sistema!", e.getMessage());
        }
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