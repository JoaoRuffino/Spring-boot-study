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
    public List<ContactResponseDTO> getAllContacts() {
        return ContactResponseDTO.transformListDTO(contactRepository.findAll());
    }


    @GetMapping("/{nome}")
    public List<ContactResponseDTO> getContactByName(@PathVariable String nome) {
        
        List<Contact> contacts = contactRepository.findByNome(nome).map(List::of).orElseGet(List::of);
        return ContactResponseDTO.transformListDTO(contacts);
    }

    
    @PostMapping
    public ContactResponseDTO createContact(@Valid @RequestBody ContactParametersDTO dto) {
        Contact contact = contactRepository.save(dto.transformToObject());
        return ContactResponseDTO.transformInDTO(contact);
    }

    @PutMapping("/{id}")
    public ContactResponseDTO updateContact(@PathVariable Long id, @RequestBody Contact updatedContact) {
        Contact existingContact = contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado: " + id));

        existingContact.setNome(updatedContact.getNome());
        existingContact.setTelefone(updatedContact.getTelefone());
        existingContact.setEmail(updatedContact.getEmail());
        Contact contactUpdated = contactRepository.save(existingContact);
        return ContactResponseDTO.transformInDTO(contactUpdated);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactRepository.deleteById(id);
    }
    
    @PatchMapping("/{id}")
    public ContactResponseDTO patchContact(@PathVariable Long id, @RequestBody Contact contactUpdates) {
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
        Contact contactUpdated = contactRepository.save(existingContact);
        return ContactResponseDTO.transformInDTO(contactUpdated);    }
}
