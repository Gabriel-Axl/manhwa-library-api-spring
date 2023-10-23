package com.alex.sololibrary.controller;

import com.alex.sololibrary.dtos.MangaDto;
import com.alex.sololibrary.models.MangaModel;
import com.alex.sololibrary.service.MangaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/manga")
public class MangaController {
    @Autowired
    MangaService mangaService;

    @PostMapping("/info")
    public ResponseEntity<Object> saveManga(@RequestBody @Valid MangaDto mangaDTO) throws ParseException {
        var mangaModel = new MangaModel();
        BeanUtils.copyProperties(mangaDTO,mangaModel);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        var dataFormatada = new Date();
        String dataString = sdf.format(dataFormatada);
        Date data = sdf.parse(dataString);
        mangaModel.setCapDate(data);

        return ResponseEntity.status(HttpStatus.CREATED).body(mangaService.save(mangaModel));
    }

    @GetMapping
    public ResponseEntity<Page<MangaModel>> getAllManga(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(mangaService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<MangaModel> mangaModelOptional = mangaService.findById(id);
        if (mangaModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manga não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mangaModelOptional.get());
    }

    @GetMapping("detail/{title}")
    public ResponseEntity<Object> getById(@PathVariable(value = "title") String title){
        Optional<MangaModel> mangaModelOptional = mangaService.findByTitle(title);
        if (mangaModelOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manga não encontrado.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(mangaModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteManga(@PathVariable(value = "id") UUID id){
        Optional<MangaModel> mangaModelOptional = mangaService.findById(id);
        if (mangaModelOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manga não encontrado.");
        }
        mangaService.delete(mangaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }
}
