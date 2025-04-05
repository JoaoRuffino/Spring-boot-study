package br.ifsp.address.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressParametersDTO {

	
	private String rua;
	private String cidade;
	private String estado;
	private String cep;
	@ManyToOne
	@JoinColumn(name = "id_contato")
	@JsonBackReference
	private Contact contato;
	
	public Address transformObject() {
		return new Address(this.rua, this.cidade, this.estado, this.cep, this.contato);
	}
}
