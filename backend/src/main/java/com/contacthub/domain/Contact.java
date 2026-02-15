package com.contacthub.domain;

import java.time.Instant;
import java.util.UUID;

public sealed interface Contact permits Contact.ContactDto {
    UUID id();
    String name();
    String phone();
    String email();
    String notes();
    Instant createdAt();
    Instant updatedAt();
    
    record ContactDto(
        UUID id,
        String name,
        String phone,
        String email,
        String notes,
        Instant createdAt,
        Instant updatedAt
    ) implements Contact {
        public static ContactDto of(UUID id, String name, String phone, String email, String notes) {
            return new ContactDto(id, name, phone, email, notes, Instant.now(), Instant.now());
        }
    }
    
    static Contact create(String name, String phone, String email, String notes) {
        return new ContactDto(
            UUID.randomUUID(),
            name,
            phone,
            email,
            notes,
            Instant.now(),
            Instant.now()
        );
    }
}
