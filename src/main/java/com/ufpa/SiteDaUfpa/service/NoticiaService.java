package com.ufpa.SiteDaUfpa.service;

import com.ufpa.SiteDaUfpa.entity.Categoria;
import com.ufpa.SiteDaUfpa.entity.Noticia;
import com.ufpa.SiteDaUfpa.exception.ResourceNotFoundException;
import com.ufpa.SiteDaUfpa.repository.CategoriaRepository;
import com.ufpa.SiteDaUfpa.repository.NoticiaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NoticiaService {
    private final NoticiaRepository noticiaRepository;
    private final CategoriaRepository categoriaRepository;
    public NoticiaService(NoticiaRepository noticiaRepository, CategoriaRepository categoriaRepository) {
        this.noticiaRepository = noticiaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Transactional
    public Noticia salvarNoticia(Noticia noticia, List<Long> categoriaIds) {
        List<Categoria> categorias = categoriaRepository.findAllById(categoriaIds);
        noticia.setCategorias(categorias);
        return noticiaRepository.save(noticia);
    }

    // BUSCAR NOTICIA PELO ID DA NOTICIA E DA CATEGORIA
    @Transactional
    public void deletarNoticia(Long id) {
        noticiaRepository.deleteById(id);
    }


    // ATUALIZAR NOTICIA E SUA CATEGORIA

    /*
    public Noticia atualizarNoticia(Long noticiaId, Noticia noticiaAtualizada) {
        Noticia noticia = noticiaRepository.findById(noticiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia não encontrada com o ID: " + noticiaId));

        // Atualiza os atributos da notícia com os valores do objeto noticiaAtualizada
        noticia.setTitulo(noticiaAtualizada.getTitulo());
        noticia.setDescricao(noticiaAtualizada.getDescricao());
        noticia.setTexto(noticiaAtualizada.getTexto());
        noticia.setImagem(noticiaAtualizada.getImagem());
        noticia.setData(noticiaAtualizada.getData());
        noticia.setHora(noticiaAtualizada.getHora());

        return noticiaRepository.save(noticia);
    }

     */

    public Noticia atualizarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    public Categoria getCategoriaById(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada com o ID: " + categoriaId));
    }
    public Noticia getNoticiaById(Long noticiaId) {
        return noticiaRepository.findById(noticiaId)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia não encontrada com o ID: " + noticiaId));
    }





    // LISTA TODAS AS NOTICIAS E SUAS RESPECTIVAS CATEGORIAS
    public List<Noticia> listarNoticiasComCategorias() {
        return noticiaRepository.findAll();
    }

    // LISTA POR CATEGORIA
    public List<Noticia> findByCategoriasId(Long categoriaId) {
        return noticiaRepository.findByCategoriasId(categoriaId);
    }

    // BUSCAR POR NOME
    public List<Noticia> buscarPorNome(String nome) {
        return noticiaRepository.buscarPorNome(nome);
    }

    // MOSTRAR NOTICIAS DA PAGINA INICIAL //////////////////////////////////////////////////////////////////////////////
    public Noticia obterDestaque() {
        return noticiaRepository.destaque();
    }

    public Noticia obterDestaque1() {
        return noticiaRepository.destaque1();
    }

    public Noticia obterDestaque2() {
        return noticiaRepository.destaque2();
    }

    public Noticia obterDestaque3() {
        return noticiaRepository.destaque3();
    }

    public Noticia obterDestaque4() {
        return noticiaRepository.destaque4();
    }


    // SUB DESTAQUE //////////////////////////////////////////////////////////////////////////////////
    public Noticia obterSubDestaque1() {
        return noticiaRepository.subDestaque1();
    }

    public Noticia obterSubDestaque2() {
        return noticiaRepository.subDestaque2();
    }

    public Noticia obterSubDestaque3() {
        return noticiaRepository.subDestaque3();
    }

    public Noticia obterSubDestaque4() {
        return noticiaRepository.subDestaque4();
    }

    // NOTICIA ABAIXO DO SUB DESTAQUE ///////////////////////////////////////////////////////////////////////////
    public Noticia obterNoticia1() {
        return noticiaRepository.noticia1();
    }

    public Noticia obterNoticia2() {
        return noticiaRepository.noticia2();
    }

    public Noticia obterNoticia3() {
        return noticiaRepository.noticia3();
    }

    public Noticia obterNoticia4() {
        return noticiaRepository.noticia4();
    }

    public Noticia obterNoticia5() {
        return noticiaRepository.noticia5();
    }

    public Noticia obterNoticia6() {
        return noticiaRepository.noticia6();
    }



 ////////////////////////////////////////////////////////////////////////////////////////////////////////

    // MOSTRAR A ULTIMA NOTICIA CADASTRADA POR CATEGORIA
    public Noticia buscarUltimaNoticiaPorCategoria(Long categoriaId) {

        return noticiaRepository.findTopByCategoriaIdOrderByIdDesc(categoriaId);
    }

    // BUSCAR NOTICIA PELO ID DA NOTICIA E DA CATEGORIA
    public Optional<Noticia> buscarNoticiaPorId(Long id) {
        return noticiaRepository.findById(id);
    }
}

