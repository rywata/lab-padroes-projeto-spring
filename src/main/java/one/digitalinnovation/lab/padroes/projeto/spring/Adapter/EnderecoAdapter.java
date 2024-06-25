package one.digitalinnovation.lab.padroes.projeto.spring.Adapter;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Endereco;
import one.digitalinnovation.lab.padroes.projeto.spring.DTO.ClienteDTO;

/**
 * Implementado para mapear dados entre a API e o serviço de endereço externo
 * Ajuda a traduzir os dados da API externa para o formato que a aplicação entende
 *
 *
 * */

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
