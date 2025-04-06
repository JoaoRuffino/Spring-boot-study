package br.ifsp.contacts.dto;


import java.util.ArrayList;
import java.util.List;

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
    
    public static List<ContactResponseDTO> transformListDTO(List<Contact> contacts){
    	List<ContactResponseDTO> dtoList = new ArrayList<>();
    	
    	for(Contact contact : contacts) {
    		dtoList.add(ContactResponseDTO.transformInDTO(contact));
    	}
    	return dtoList;
    }


}