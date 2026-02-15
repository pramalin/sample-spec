package com.contacthub.repository;

import com.contacthub.domain.ContactEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, UUID> {
    
    @Query("SELECT c FROM ContactEntity c WHERE c.deletedAt IS NULL AND " +
           "(LOWER(c.name) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "c.phone LIKE CONCAT('%', :query, '%'))")
    List<ContactEntity> search(@Param("query") String query);
    
    @Query("SELECT c FROM ContactEntity c WHERE c.deletedAt IS NULL")
    Page<ContactEntity> findAllActive(Pageable pageable);
    
    @Query("SELECT c FROM ContactEntity c WHERE c.deletedAt IS NULL AND " +
           "(:categoryId IS NULL OR EXISTS (SELECT 1 FROM c.categories cat WHERE cat.id = :categoryId))")
    Page<ContactEntity> findAllByCategoryId(UUID categoryId, Pageable pageable);
}
