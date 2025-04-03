package br.ifsp.contacts.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ifsp.contacts.model.Address;
import br.ifsp.contacts.model.Contact;
import br.ifsp.contacts.repository.AddressRepository;
import br.ifsp.contacts.repository.ContactRepository;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @PostMapping
    public Address createAddress(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @GetMapping("/contacts/{id}")
    public ResponseEntity<List<Address>> getAddressesByContact(@PathVariable Long id) {
        Contact contact = contactRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Contato não encontrado"));

        List<Address> addresses = addressRepository.findByContato(contact);
        return ResponseEntity.ok(addresses);
    }
}
