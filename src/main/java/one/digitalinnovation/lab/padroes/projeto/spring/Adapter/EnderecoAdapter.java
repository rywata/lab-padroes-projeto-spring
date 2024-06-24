package one.digitalinnovation.lab.padroes.projeto.spring.Adapter;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Endereco;
import one.digitalinnovation.lab.padroes.projeto.spring.DTO.ClienteDTO;

public class EnderecoAdapter {

    public static Endereco toEndereco(ClienteDTO clienteDTO) {
        Endereco endereco = new Endereco();
        endereco.setCep(clienteDTO.getCep());
        endereco.setLogradouro(clienteDTO.getLogradouro());
        endereco.setComplemento(clienteDTO.getComplemento());
        endereco.setBairro(clienteDTO.getBairro());
        endereco.setLocalidade(clienteDTO.getLocalidade());
        endereco.setUf(clienteDTO.getUf());
        return endereco;
    }
}
