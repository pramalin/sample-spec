package com.contacthub.api;

import com.contacthub.domain.ContactEntity;
import com.contacthub.service.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    
    private final ContactService contactService;
    
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }
    
    @GetMapping
    public ResponseEntity<Page<ContactEntity>> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(contactService.getAllContacts(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ContactEntity> getContactById(@PathVariable UUID id) {
        return contactService.getContactById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<ContactEntity> createContact(@RequestBody ContactRequest request) {
        ContactEntity contact = contactService.createContact(
            request.name(),
            request.phone(),
            request.email(),
            request.notes()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ContactEntity> updateContact(
            @PathVariable UUID id,
            @RequestBody ContactRequest request) {
        return contactService.updateContact(id, request.name(), request.phone(), request.email(), request.notes())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable UUID id) {
        return contactService.deleteContact(id)
            .map(c -> ResponseEntity.noContent().<ResponseEntity<?>>build())
            .orElse(ResponseEntity.notFound().<ResponseEntity<?>>build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<ContactEntity>> searchContacts(@RequestParam String q) {
        return ResponseEntity.ok(contactService.searchContacts(q));
    }
    
    public record ContactRequest(String name, String phone, String email, String notes) {}
}
