package com.comunippon.ComuNippon.service;

import com.comunippon.ComuNippon.model.Tag;
import com.comunippon.ComuNippon.repository.TagRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@AllArgsConstructor
public class TagService {

    private final TagRepository repository;

    public void createTag(Tag tagDTO) {
        Tag tag = new Tag();
        tag.setName(tagDTO.getName());
        repository.save(tag);
    }

    public List<Tag> listTag() {
        return repository.findAll();
    }

    public Tag getTagById(Long id) {
        return repository.findById(id).get();
    }

    public Tag deleteTag(Long id) {
        Tag Tag = getTagById(id);
        repository.deleteById(id);
        return Tag;
    }

    public Tag updateTag(Long id, Tag tagDTO) {
        Tag tag = getTagById(id);
        tag.setName(tagDTO.getName());
        return repository.save(tag);
        
    }

}
