package br.ifsp.contacts.dto;


import br.ifsp.contacts.model.Contact;
import lombok.Getter;

@Getter
public class ContactResponseDTO {
	private String nome;
    private String telefone;
    
    private ContactResponseDTO(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;
    }

    public static ContactResponseDTO transformInDTO(Contact contact) {
    	return new ContactResponseDTO(contact.getNome(), contact.getTelefone());
    }


}