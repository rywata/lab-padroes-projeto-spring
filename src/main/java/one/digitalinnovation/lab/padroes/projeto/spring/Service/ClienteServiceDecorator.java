package one.digitalinnovation.lab.padroes.projeto.spring.Service;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;

public class ClienteServiceDecorator implements ClienteService {

    private final ClienteService clienteService;

    public ClienteServiceDecorator(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    public Iterable<Cliente> buscarTodos() {
        System.out.println("Log: buscando todos os clientes");
        return clienteService.buscarTodos();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        System.out.println("Log: buscando cliente por ID " + id);
        return clienteService.buscarPorId(id);
    }

    @Override
    public void inserir(Cliente cliente) {
        System.out.println("Log: inserindo cliente");
        clienteService.inserir(cliente);
    }

    @Override
    public void atualizar(Long id, Cliente cliente) {
        System.out.println("Log: atualizando cliente com ID " + id);
        clienteService.atualizar(id, cliente);
    }

    @Override
    public void deletar(Long id) {
        System.out.println("Log: deletando cliente com ID " + id);
        clienteService.deletar(id);
    }
}
