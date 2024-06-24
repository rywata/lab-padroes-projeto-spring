package one.digitalinnovation.lab.padroes.projeto.spring.Factory;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.Endereco;

public class ClienteFactory {

    public static Cliente createCliente(String nome, Endereco endereco) {
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        return cliente;
    }
}
