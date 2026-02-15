package com.contacthub.service;

import com.contacthub.domain.ContactEntity;
import com.contacthub.repository.ContactRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ContactService {
    
    private final ContactRepository contactRepository;
    
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }
    
    public Page<ContactEntity> getAllContacts(Pageable pageable) {
        return contactRepository.findAllActive(pageable);
    }
    
    public Optional<ContactEntity> getContactById(UUID id) {
        return contactRepository.findById(id)
            .filter(c -> c.getDeletedAt() == null);
    }
    
    public ContactEntity createContact(String name, String phone, String email, String notes) {
        ContactEntity contact = new ContactEntity();
        contact.setName(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        contact.setNotes(notes);
        return contactRepository.save(contact);
    }
    
    public Optional<ContactEntity> updateContact(UUID id, String name, String phone, String email, String notes) {
        return contactRepository.findById(id)
            .filter(c -> c.getDeletedAt() == null)
            .map(contact -> {
                contact.setName(name);
                contact.setPhone(phone);
                contact.setEmail(email);
                contact.setNotes(notes);
                return contactRepository.save(contact);
            });
    }
    
    public Optional<ContactEntity> deleteContact(UUID id) {
        return contactRepository.findById(id)
            .filter(c -> c.getDeletedAt() == null)
            .map(contact -> {
                contact.setDeletedAt(Instant.now());
                return contactRepository.save(contact);
            });
    }
    
    public List<ContactEntity> searchContacts(String query) {
        return contactRepository.search(query);
    }
}
