package br.com.fiap.gsjava.DTO;

import br.com.fiap.gsjava.model.UsuarioRole;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UsuarioRequest {
    @NotBlank(message="O nome é obrigatório!")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;
    @NotBlank(message="O cpf é obrigatório!")
    @Pattern(
            regexp = "\\d{11}",
            message = "O CPF deve conter exatamente 11 dígitos numéricos (somente números)"
    )
    private String cpf;
    @NotBlank(message="O email é obrigatório!")
    @Email(message = "Insira um email válido")

    private String email;
    @NotBlank(message="A senha é obrigatória!")
    @Size(min = 2, max = 100, message = "A senha deve ter pelo menos 2 caracteres e no máximo 100")
    private String senha;

    private UsuarioRole role;

    public UsuarioRole getRole() {
        return role;
    }

    public void setRole(UsuarioRole role) {
        this.role = role;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
