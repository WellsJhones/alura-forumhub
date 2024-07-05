package br.com.alura.forumhub.forum.hub.repository;

import br.com.alura.forumhub.forum.hub.domain.curso.Curso;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Page<Curso> findAllByAtivoTrue(Pageable pageable);

    Curso findByNome(String curso);
}
