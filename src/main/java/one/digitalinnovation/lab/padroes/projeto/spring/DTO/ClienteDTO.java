package one.digitalinnovation.lab.padroes.projeto.spring.DTO;


/**
 * DTOs são usados para transferir dados entre partes do sistema.
 * ClienteDTO e EnderecoDTO contêm apenas os dados necessários para
 * transferir informações de clientes e endereços.
 *
 * Controlador converte objetos de domínio (ex: Cliente) para DTOs
 * (ClienteDTO) antes de enviar ao cliente, e vice-versa.
 *
 *
 */


public class ClienteDTO {
    private Long id;
    private String nome;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
