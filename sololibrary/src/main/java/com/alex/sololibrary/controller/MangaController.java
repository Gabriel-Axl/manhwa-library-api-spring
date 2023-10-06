package com.alex.sololibrary.controller;

import com.alex.sololibrary.dtos.MangaDto;
import com.alex.sololibrary.models.MangaModel;
import com.alex.sololibrary.service.MangaService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/image/{id}")
    public ResponseEntity<Object> saveMangaImage(@RequestParam("file") MultipartFile file, @PathVariable(value = "id") UUID id) throws ParseException {
        Optional<MangaModel> mangaModelOptional = mangaService.findById(id);

        if (mangaModelOptional.isPresent()) {
            MangaModel mangaModel = mangaModelOptional.get();

            try {
                if (!file.isEmpty()) {
                    byte[] bytes =  file.getBytes();
                    Path caminho = Paths.get(MangaService.pathImagem + String.valueOf(mangaModel.getId()) + file.getOriginalFilename());
                    Files.write(caminho, bytes);
                    mangaModel.setNomeImg(MangaService.pathImagem + String.valueOf(mangaModel.getId()) + file.getOriginalFilename());
                    mangaService.save(mangaModel); // Salvar as informações atualizadas do manga
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Imagem salva com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Manga não encontrado");
        }
    }

}
