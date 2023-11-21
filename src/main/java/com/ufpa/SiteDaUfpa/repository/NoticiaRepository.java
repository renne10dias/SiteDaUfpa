package com.ufpa.SiteDaUfpa.repository;

import com.ufpa.SiteDaUfpa.entity.Noticia;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia, Long> {

    // FAZ PARTE DA FUNÇÃO QUE VAI BUSCAR PELO NOME
    @Query(value = "select u from Noticia u where upper(trim(u.titulo)) like %?1%")
    List<Noticia> buscarPorNome(String name);

    // RETORNA TODOS
    @EntityGraph(attributePaths = "categorias")
    List<Noticia> findAll();

    // FAZ A BUSCA POR CATEGORIA
    List<Noticia> findByCategoriasId(Long categoriaId);



    // RETORNA A POSTAGEM MAIS RECENTE, PAGINA INICIAL > DESTAQUE PRINCIPAL
    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Noticia destaque();


    // RETORNA AS POSTAGEM MAIS RECENTES EM ORDEM DESCRECENTE > DESTAQUE
    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 1", nativeQuery = true)
    Noticia destaque1();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 2", nativeQuery = true)
    Noticia destaque2();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 3", nativeQuery = true)
    Noticia destaque3();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 4", nativeQuery = true)
    Noticia destaque4();


    // RETORNA AS POSTAGEM MAIS RECENTES EM ORDEM DESCRECENTE > SUB DESTAQUE
    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 5", nativeQuery = true)
    Noticia subDestaque1();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 6", nativeQuery = true)
    Noticia subDestaque2();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 7", nativeQuery = true)

    Noticia subDestaque3();
    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 8", nativeQuery = true)
    Noticia subDestaque4();


    // NOTICIA ABAIXO DO SUB DESTAQUE ///////////////////////////////////////////////////////////////////////////

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 9", nativeQuery = true)
    Noticia noticia1();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 10", nativeQuery = true)
    Noticia noticia2();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 11", nativeQuery = true)
    Noticia noticia3();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 12", nativeQuery = true)
    Noticia noticia4();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 13", nativeQuery = true)
    Noticia noticia5();

    @Query(value = "SELECT * FROM tb_noticia ORDER BY id DESC LIMIT 1 OFFSET 14", nativeQuery = true)
    Noticia noticia6();

    // RETORNA A ULTIMA POSTAGEM POR CATEGORIA
    @Query(value = "SELECT * FROM tb_noticia n INNER JOIN tb_noticia_has_tb_categoria nc ON n.id = nc.tb_noticia_id WHERE nc.tb_categoria_id = :categoriaId ORDER BY n.id DESC LIMIT 1", nativeQuery = true)
    Noticia findTopByCategoriaIdOrderByIdDesc(Long categoriaId);

    // BUSCAR NOTICIA POR ID
    @EntityGraph(attributePaths = "categorias")
    Optional<Noticia> findById(Long id);

}