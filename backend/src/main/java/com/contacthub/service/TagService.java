package com.contacthub.service;

import com.contacthub.domain.TagEntity;
import com.contacthub.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TagService {
    
    private final TagRepository tagRepository;
    
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    
    public List<TagEntity> getAllTags() {
        return tagRepository.findAll();
    }
    
    public Optional<TagEntity> getTagById(UUID id) {
        return tagRepository.findById(id);
    }
    
    public TagEntity createTag(String name) {
        TagEntity tag = new TagEntity();
        tag.setName(name);
        return tagRepository.save(tag);
    }
    
    public Optional<TagEntity> updateTag(UUID id, String name) {
        return tagRepository.findById(id)
            .map(tag -> {
                tag.setName(name);
                return tagRepository.save(tag);
            });
    }
    
    public boolean deleteTag(UUID id) {
        return tagRepository.findById(id)
            .map(tag -> {
                tagRepository.delete(tag);
                return true;
            })
            .orElse(false);
    }
}
