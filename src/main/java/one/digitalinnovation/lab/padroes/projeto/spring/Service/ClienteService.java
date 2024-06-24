package one.digitalinnovation.lab.padroes.projeto.spring.Service;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;


public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    void inserir(Cliente cliente);

    void atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}