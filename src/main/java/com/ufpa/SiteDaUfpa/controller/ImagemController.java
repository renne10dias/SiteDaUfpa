package com.ufpa.SiteDaUfpa.controller;

import com.ufpa.SiteDaUfpa.entity.Noticia;
import com.ufpa.SiteDaUfpa.repository.NoticiaRepository;
import jakarta.annotation.Resource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@RestController
@RequestMapping("/api/imagem")
public class ImagemController {

    private static String caminhoImagem = "src/main/resources/file/";
    private final NoticiaRepository noticiaRepository;

    public ImagemController(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    @GetMapping("/noticia/{noticiaId}")
    public ResponseEntity<byte[]> exibirImagemPorNoticiaId(@PathVariable Long noticiaId) throws IOException {
        Optional<Noticia> noticiaOptional = noticiaRepository.findById(noticiaId);

        if (noticiaOptional.isPresent()) {
            Noticia noticia = noticiaOptional.get();
            String nomeImagem = noticia.getImagem();
            Path caminhoCompleto = Paths.get(caminhoImagem, nomeImagem);
            byte[] imagemBytes = Files.readAllBytes(caminhoCompleto);

            return ResponseEntity.ok()
                    .contentLength(imagemBytes.length)
                    .body(imagemBytes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

