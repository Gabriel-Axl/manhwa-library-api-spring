package com.alex.sololibrary.service;

import com.alex.sololibrary.models.MangaModel;
import com.alex.sololibrary.repository.MangaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MangaService {

    @Autowired
    public MangaRepository mangaRepository;

    public MangaModel save(MangaModel mangaModel){
        return mangaRepository.save(mangaModel);
    }

    public Page<MangaModel> findAll(Pageable pageable){
        return mangaRepository.findAll(pageable);
    }

    public Optional<MangaModel> findById(UUID id){
        return mangaRepository.findById(id);
    }

    public Optional<MangaModel> findByTitle(String title){
        return mangaRepository.findByTitleContaining(title);
    }

    public void delete(MangaModel mangaModel) { mangaRepository.delete(mangaModel);}




}
