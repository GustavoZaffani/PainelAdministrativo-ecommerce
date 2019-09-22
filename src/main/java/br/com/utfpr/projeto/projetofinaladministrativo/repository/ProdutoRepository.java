package br.com.utfpr.projeto.projetofinaladministrativo.repository;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
