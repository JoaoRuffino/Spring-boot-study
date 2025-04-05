package br.ifsp.contacts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifsp.contacts.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByNome(String nome);
}
