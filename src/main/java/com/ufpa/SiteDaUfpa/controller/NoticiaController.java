package com.ufpa.SiteDaUfpa.controller;

import com.ufpa.SiteDaUfpa.entity.Categoria;
import com.ufpa.SiteDaUfpa.entity.Noticia;
import com.ufpa.SiteDaUfpa.service.NoticiaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/noticia")
public class NoticiaController {

    private final NoticiaService noticiaService;
    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/listar")
    public ResponseEntity<List<Noticia>> listarNoticiasComCategorias() {
        List<Noticia> noticias = noticiaService.listarNoticiasComCategorias();
        return ResponseEntity.ok(noticias);
    }

    // EXIBE A NOTICIA RELACIONADA AO ID DA CATEGORIA
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscaPorCategoria/{categoriaId}")
    public ResponseEntity<List<Noticia>> buscarPorCategoria(@PathVariable Long categoriaId) {
        List<Noticia> noticias = noticiaService.findByCategoriasId(categoriaId);
        return ResponseEntity.ok(noticias);
    }


    @PostMapping("/postar")
    @ResponseBody
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> criarNoticia(@RequestBody Noticia noticia, @RequestParam("categoriaIds") List<Long> categoriaIds) {
        Noticia novaNoticia = noticiaService.salvarNoticia(noticia, categoriaIds);
        return ResponseEntity.ok(novaNoticia);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/buscarPorNome")
    public ResponseEntity<List<Noticia>> buscarPorNome(@RequestParam("nome") String nome) {
        List<Noticia> noticias = noticiaService.buscarPorNome(nome);
        return ResponseEntity.ok(noticias);
    }


    //// DELETAR UMA NOTICIA
    @DeleteMapping("deletar/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<String> deletarNoticia(@PathVariable Long id) {
        try {
            noticiaService.deletarNoticia(id);
            return ResponseEntity.ok("Notícia deletada com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao deletar notícia: " + e.getMessage());
        }
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Notícia não encontrada: " + e.getMessage());
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////

    /*
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Noticia> atualizarNoticia(@PathVariable("id") Long noticiaId, @RequestBody Noticia noticia) {
        Noticia noticiaAtualizada = noticiaService.atualizarNoticia(noticiaId, noticia);
        return ResponseEntity.ok(noticiaAtualizada);
    }

     */


    @PutMapping("/atualizar/{noticiaId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> atualizarNoticia(@PathVariable Long noticiaId, @RequestBody Noticia noticia, @RequestParam(required = false) Long categoriaId) {
        Noticia noticiaAtual = noticiaService.getNoticiaById(noticiaId);

        // Atualiza os atributos da notícia
        noticiaAtual.setTitulo(noticia.getTitulo());
        noticiaAtual.setDescricao(noticia.getDescricao());
        noticiaAtual.setTexto(noticia.getTexto());
        noticiaAtual.setImagem(noticia.getImagem());

        if (categoriaId != null) {
            Categoria categoria = noticiaService.getCategoriaById(categoriaId);
            List<Categoria> categoriasAtualizadas = new ArrayList<>();
            categoriasAtualizadas.add(categoria);
            noticiaAtual.setCategorias(categoriasAtualizadas);
        }

        Noticia noticiaAtualizada = noticiaService.atualizarNoticia(noticiaAtual);
        return ResponseEntity.ok(noticiaAtualizada);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/destaque")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterDestaque() {
        Noticia noticia = noticiaService.obterDestaque();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destaque1")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterDestaque1() {
        Noticia noticia = noticiaService.obterDestaque1();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destaque2")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterDestaque2() {
        Noticia noticia = noticiaService.obterDestaque2();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destaque3")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterDestaque3() {
        Noticia noticia = noticiaService.obterDestaque3();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destaque4")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterDestaque4() {
        Noticia noticia = noticiaService.obterDestaque4();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/subDestaque1")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterSubDestaque1() {
        Noticia noticia = noticiaService.obterSubDestaque1();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subDestaque2")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterSubDestaque2() {
        Noticia noticia = noticiaService.obterSubDestaque2();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subDestaque3")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterSubDestaque3() {
        Noticia noticia = noticiaService.obterSubDestaque3();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/subDestaque4")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterSubDestaque4() {
        Noticia noticia = noticiaService.obterSubDestaque4();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    // NOTICIA ABAIXO DO SUB DESTAQUE ///////////////////////////////////////////////////////////////////////////
    @GetMapping("/noticia1")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterNoticia1() {
        Noticia noticia = noticiaService.obterNoticia1();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/noticia2")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterNoticia2() {
        Noticia noticia = noticiaService.obterNoticia2();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/noticia3")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterNoticia3() {
        Noticia noticia = noticiaService.obterNoticia3();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/noticia4")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterNoticia4() {
        Noticia noticia = noticiaService.obterNoticia4();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/noticia5")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterNoticia5() {
        Noticia noticia = noticiaService.obterNoticia5();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/noticia6")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> obterNoticia6() {
        Noticia noticia = noticiaService.obterNoticia6();
        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/recentePorCategoria/{categoriaId}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> buscarUltimaNoticiaPorCategoria(@PathVariable Long categoriaId) {
        Noticia ultimaNoticia = noticiaService.buscarUltimaNoticiaPorCategoria(categoriaId);
        if (ultimaNoticia == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ultimaNoticia);
    }

    @GetMapping("/buscar/{id}")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Noticia> buscarNoticiaPorId(@PathVariable("id") Long id) {
        Optional<Noticia> noticiaOptional = noticiaService.buscarNoticiaPorId(id);
        if (noticiaOptional.isPresent()) {
            Noticia noticia = noticiaOptional.get();
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}

