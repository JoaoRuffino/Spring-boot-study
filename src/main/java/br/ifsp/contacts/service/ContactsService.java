package br.ifsp.contacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import br.ifsp.contacts.model.*;
import br.ifsp.contacts.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
@Service
public class ContactsService {

	
	@Autowired
    private ContactRepository contactRepository;
	
	public Page<Contact> findAllPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("nome").ascending());
        return contactRepository.findAll(pageable);
    }
}
