package com.ufpa.SiteDaUfpa.service;

import com.ufpa.SiteDaUfpa.entity.Categoria;
import com.ufpa.SiteDaUfpa.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    private CategoriaRepository categoriaRepository;
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }
    public Categoria salvarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }
}
