package br.ifsp.address.dto;


import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;

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
	
	private Contact contato;
	
	public Address transformObject() {
		return new Address(this.rua, this.cidade, this.estado, this.cep, this.contato);
	}
}
