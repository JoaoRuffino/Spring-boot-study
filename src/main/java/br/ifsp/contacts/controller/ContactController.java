package br.ifsp.contacts.controller;

import br.ifsp.contacts.dto.ContactParametersDTO;
import br.ifsp.contacts.dto.ContactResponseDTO;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.ContactRepository;
import br.ifsp.contacts.service.ContactsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;


import java.util.List;
@Validated
@RestController
@RequestMapping("/api/contacts")
@Tag(name = "Contact", description = "Controller for contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactsService contactService;

    @GetMapping
    @Operation(summary = "Retorna lista de todos os usuários", description = "Método para retornar usuários")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<Page<Contact>> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Contact> contactPage = contactService.findAllPaginated(page, size);
        return ResponseEntity.ok(contactPage);
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
