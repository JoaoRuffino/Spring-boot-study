package br.ifsp.contacts.service;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;

import br.ifsp.contacts.repository.ContactRepository;

public class ContactsService {

	
	@Autowired
    private ContactRepository contactRepository;
	
	
}
