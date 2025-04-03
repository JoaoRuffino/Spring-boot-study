package br.ifsp.contacts.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;



@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Contact {

    
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;
    @Size(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
    @Pattern(regexp = "\\d+", message = "O telefone deve conter apenas números")
    private String telefone;
    @Email(message = "O email deve ter um formato válido")
    private String email;
    @OneToMany(mappedBy = "contato", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;


    public Contact() {}

    public Contact(String nome, String telefone, String email) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
}
