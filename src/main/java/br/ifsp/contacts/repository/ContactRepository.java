package br.ifsp.contacts.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ifsp.contacts.model.Contact;

/**
 * Esta interface extende JpaRepository, que nos fornece métodos prontos 
 * para acessar e manipular dados no banco de dados. Basta especificar 
 * a classe de entidade (Contact) e o tipo da chave primária (Long).
 */
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByNome(String nome);
}
