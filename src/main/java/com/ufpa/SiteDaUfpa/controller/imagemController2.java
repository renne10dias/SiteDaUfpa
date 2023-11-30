package com.ufpa.SiteDaUfpa.controller;

import com.ufpa.SiteDaUfpa.entity.Noticia;
import com.ufpa.SiteDaUfpa.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/imagens")
public class imagemController2 {

    @Autowired
    private NoticiaService noticiaService;
    private static String caminhoImagem = "src/main/resources/file/";

    @GetMapping("/noticia/{id}")
    public ResponseEntity<byte[]> obterImagemPorNoticiaId(@PathVariable Long id) throws IOException {
        Noticia noticia = noticiaService.getNoticiaById(id);

        if (noticia == null || noticia.getImagem() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Path caminho = Paths.get(caminhoImagem, noticia.getImagem());

        byte[] imagemBytes = Files.readAllBytes(caminho);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        return new ResponseEntity<>(imagemBytes, headers, HttpStatus.OK);
    }
}
