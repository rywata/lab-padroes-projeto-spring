package one.digitalinnovation.lab.padroes.projeto.spring.Controller;

import one.digitalinnovation.lab.padroes.projeto.spring.Model.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import one.digitalinnovation.lab.padroes.projeto.spring.DTO.ClienteDTO;
import one.digitalinnovation.lab.padroes.projeto.spring.Model.Cliente;
import one.digitalinnovation.lab.padroes.projeto.spring.Service.ClienteService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("clientes")
public class ClienteRestController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> buscarTodos() {
        List<ClienteDTO> clientesDTO = StreamSupport.stream(clienteService.buscarTodos().spliterator(), false)
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        Cliente cliente = clienteService.buscarPorId(id);
        return ResponseEntity.ok(toClienteDTO(cliente));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> inserir(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = toCliente(clienteDTO);
        clienteService.inserir(cliente);
        return ResponseEntity.ok(toClienteDTO(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = toCliente(clienteDTO);
        clienteService.atualizar(id, cliente);
        return ResponseEntity.ok(toClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }

    private ClienteDTO toClienteDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setNome(cliente.getNome());
        if (cliente.getEndereco() != null) {
            dto.setCep(cliente.getEndereco().getCep());
            dto.setLogradouro(cliente.getEndereco().getLogradouro());
            dto.setComplemento(cliente.getEndereco().getComplemento());
            dto.setBairro(cliente.getEndereco().getBairro());
            dto.setLocalidade(cliente.getEndereco().getLocalidade());
            dto.setUf(cliente.getEndereco().getUf());
        }
        return dto;
    }

    private Cliente toCliente(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setNome(dto.getNome());
        if (dto.getCep() != null) {
            Endereco endereco = new Endereco();
            endereco.setCep(dto.getCep());
            endereco.setLogradouro(dto.getLogradouro());
            endereco.setComplemento(dto.getComplemento());
            endereco.setBairro(dto.getBairro());
            endereco.setLocalidade(dto.getLocalidade());
            endereco.setUf(dto.getUf());
            cliente.setEndereco(endereco);
        }
        return cliente;
    }
}
