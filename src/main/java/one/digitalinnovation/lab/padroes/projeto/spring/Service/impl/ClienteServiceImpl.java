package one.digitalinnovation.lab.padroes.projeto.spring.Service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.ClienteRepository;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.Endereco;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.EnderecoRepository;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.ClienteService;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.ViaCepService;
import one.digitalinnovation.lab.padroes.projeto.spring.Excecoes.ViaCepException;

/**
 * Implementação do serviço de clientes que realiza operações de CRUD e integração com serviço de consulta de CEP.
 */
@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElse(null);
    }

    @Override
    public Cliente inserir(Cliente cliente) {
        return salvarClienteComCep(cliente);
    }

    @Override
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            Cliente clienteExistente = clienteBd.get();
            clienteExistente.setNome(clienteAtualizado.getNome());
            return salvarClienteComCep(clienteExistente);
        }
        return null;
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private Cliente salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();

        // Busca o endereço por CEP no banco de dados
        Optional<Endereco> enderecoOptional = enderecoRepository.findByCep(cep);

        // Tenta buscar o endereço no ViaCEP se não for encontrado no banco de dados
        Endereco endereco = enderecoOptional.orElseGet(() -> {
            try {
                return viaCepService.buscarEnderecoPorCep(cep);
            } catch (ViaCepException e) {
                throw new ViaCepException("Endereço não encontrado no banco de dados e falha ao buscar no ViaCEP: " + cep, e);
            }
        });

        // Se ainda assim o endereço for nulo, lança uma exceção
        if (endereco == null) {
            throw new ViaCepException("Endereço não encontrado para o CEP: " + cep);
        }

        // Salvar cliente com endereço
        cliente.setEndereco(endereco);
        return clienteRepository.save(cliente);
    }
}
