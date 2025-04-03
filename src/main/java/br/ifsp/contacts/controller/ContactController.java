package br.ifsp.contacts.controller;

import br.ifsp.contacts.dto.ContactParametersDTO;
import br.ifsp.contacts.dto.ContactResponseDTO;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/api/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


    @GetMapping("/{nome}")
    public List<Contact> getContactByName(@PathVariable String nome) {
        
        return contactRepository.findByNome(nome)
                .map(List::of)
                .orElseGet(List::of);
    }

    
    @PostMapping
    public ContactResponseDTO createContact(@Valid @RequestBody ContactParametersDTO dto) {
    	
    	
    	
        Contact contact = contactRepository.save(dto.transformToObject());
        
        
        return ContactResponseDTO.transformInDTO(contact);
    }

    @PutMapping("/{id}")
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

        existingContact.setNome(updatedContact.getNome());
        existingContact.setTelefone(updatedContact.getTelefone());
        existingContact.setEmail(updatedContact.getEmail());
        return contactRepository.save(existingContact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
    
    @PatchMapping("/{id}")
    public Contact patchContact(@PathVariable Long id, @RequestBody Contact contactUpdates) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));
        
        if (contactUpdates.getNome() != null) {
            existingContact.setNome(contactUpdates.getNome());
        }
        if (contactUpdates.getTelefone() != null) {
            existingContact.setTelefone(contactUpdates.getTelefone());
        }
        if (contactUpdates.getEmail() != null) {
            existingContact.setEmail(contactUpdates.getEmail());
        }
        return contactRepository.save(existingContact);
    }
}
