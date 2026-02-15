package com.contacthub.api;

import com.contacthub.domain.TagEntity;
import com.contacthub.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tags")
public class TagController {
    
    private final TagService tagService;
    
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    
    @GetMapping
    public ResponseEntity<List<TagEntity>> getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<TagEntity> getTagById(@PathVariable UUID id) {
        return tagService.getTagById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<TagEntity> createTag(@RequestBody TagRequest request) {
        TagEntity tag = tagService.createTag(request.name());
        return ResponseEntity.status(HttpStatus.CREATED).body(tag);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<TagEntity> updateTag(
            @PathVariable UUID id,
            @RequestBody TagRequest request) {
        return tagService.updateTag(id, request.name())
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTag(@PathVariable UUID id) {
        boolean deleted = tagService.deleteTag(id);
        return deleted 
            ? ResponseEntity.noContent().<ResponseEntity<?>>build() 
            : ResponseEntity.notFound().<ResponseEntity<?>>build();
    }
    
    public record TagRequest(String name) {}
}
