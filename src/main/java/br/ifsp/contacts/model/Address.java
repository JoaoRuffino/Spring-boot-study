package br.ifsp.contacts.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	private String rua;
	private String cidade;
	private String estado;
	private String cep;
	@ManyToOne
	@JoinColumn(name = "id_contato")
	@JsonBackReference
	private Contact contato;
	
	public Address() {}
	
	

	public Address(String rua, String cidade, String estado, String cep, Contact contato) {
		super();
		this.rua = rua;
		this.cidade = cidade;
		this.estado = estado;
		this.cep = cep;
		this.contato = contato;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Contact getContato() {
		return contato;
	}

	public void setContato(Contact contato) {
		this.contato = contato;
	}
	
}
