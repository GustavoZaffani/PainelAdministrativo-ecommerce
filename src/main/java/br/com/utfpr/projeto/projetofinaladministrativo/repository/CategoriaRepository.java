package br.com.utfpr.projeto.projetofinaladministrativo.repository;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
