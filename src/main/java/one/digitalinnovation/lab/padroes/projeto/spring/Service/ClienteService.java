package one.digitalinnovation.lab.padroes.projeto.spring.Service;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;

public interface ClienteService {

    Iterable<Cliente> buscarTodos();

    Cliente buscarPorId(Long id);

    Cliente inserir(Cliente cliente);

    Cliente atualizar(Long id, Cliente cliente);

    void deletar(Long id);

}
