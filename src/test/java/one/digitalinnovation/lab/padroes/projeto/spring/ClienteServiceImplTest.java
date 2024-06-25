package one.digitalinnovation.lab.padroes.projeto.spring;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.Endereco;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.ClienteRepository;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.EnderecoRepository;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.ViaCepService;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.impl.ClienteServiceImpl;
import one.digitalinnovation.lab.padroes.projeto.spring.Excecoes.ViaCepException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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
        verify(clienteRepository, times(1)).findAll();
    }

    @Test
    void testBuscarPorId() {
        Long clienteId = 1L;
        Cliente clienteMock = new Cliente();
        clienteMock.setId(clienteId);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteMock));

        Cliente cliente = clienteService.buscarPorId(clienteId);

        assertNotNull(cliente);
        assertEquals(clienteId, cliente.getId());
        verify(clienteRepository, times(1)).findById(clienteId);
    }

    @Test
    void testInserir() {
        Cliente clienteMock = new Cliente();
        clienteMock.setNome("Cliente Teste");
        clienteMock.setEndereco(new Endereco());
        clienteMock.getEndereco().setCep("12345-678");

        when(enderecoRepository.findByCep(anyString())).thenReturn(Optional.empty());
        when(viaCepService.buscarEnderecoPorCep(anyString())).thenReturn(new Endereco());
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteMock);

        Cliente clienteInserido = clienteService.inserir(clienteMock);

        assertNotNull(clienteInserido);
        verify(enderecoRepository, times(1)).findByCep(anyString());
        verify(viaCepService, times(1)).buscarEnderecoPorCep(anyString());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testAtualizar() {
        Long clienteId = 1L;
        Cliente clienteExistente = new Cliente();
        clienteExistente.setId(clienteId);
        clienteExistente.setNome("João");
        clienteExistente.setEndereco(new Endereco());
        clienteExistente.getEndereco().setCep("12345-678");

        Cliente clienteAtualizado = new Cliente();
        clienteAtualizado.setId(clienteId);
        clienteAtualizado.setNome("João da Silva");
        clienteAtualizado.setEndereco(new Endereco());
        clienteAtualizado.getEndereco().setCep("12345-678");

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));
        when(enderecoRepository.findByCep(anyString())).thenReturn(Optional.of(new Endereco()));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteAtualizado);

        Cliente resultado = clienteService.atualizar(clienteId, clienteAtualizado);

        assertNotNull(resultado);
        assertEquals(clienteAtualizado.getNome(), resultado.getNome());
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(enderecoRepository, times(1)).findByCep(anyString());
        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void testDeletar() {
        Long clienteId = 1L;

        doNothing().when(clienteRepository).deleteById(clienteId);

        clienteService.deletar(clienteId);

        verify(clienteRepository, times(1)).deleteById(clienteId);
    }

    @Test
    void testSalvarClienteComCepViaCepException() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Teste");
        Endereco endereco = new Endereco();
        endereco.setCep("12345-678");
        cliente.setEndereco(endereco);

        when(enderecoRepository.findByCep(anyString())).thenReturn(Optional.empty());
        when(viaCepService.buscarEnderecoPorCep(anyString())).thenThrow(new ViaCepException("CEP não encontrado"));

        try {
            clienteService.inserir(cliente);
        } catch (ViaCepException e) {
            assertEquals("Endereço não encontrado no banco de dados e falha ao buscar no ViaCEP: 12345-678", e.getMessage());
        }

        verify(enderecoRepository, times(1)).findByCep(anyString());
        verify(viaCepService, times(1)).buscarEnderecoPorCep(anyString());
        verify(clienteRepository, times(0)).save(any(Cliente.class));
    }
}
