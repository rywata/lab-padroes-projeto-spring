package one.digitalinnovation.lab.padroes.projeto.spring;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.impl.ClienteServiceImpl;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.ClienteRepository;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.EnderecoRepository;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.ViaCepService;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private ViaCepService viaCepService;

    @Test
    void testBuscarTodos() {
        when(clienteRepository.findAll()).thenReturn(new ArrayList<>());
        Iterable<Cliente> clientes = clienteService.buscarTodos();
        assertNotNull(clientes);
    }

}