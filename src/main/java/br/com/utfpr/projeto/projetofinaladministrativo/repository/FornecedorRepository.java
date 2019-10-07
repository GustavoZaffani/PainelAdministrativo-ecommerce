package br.com.utfpr.projeto.projetofinaladministrativo.repository;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    @Query("SELECT f FROM Fornecedor f WHERE UPPER(f.nomeFantasia) LIKE :nome OR UPPER(f.razaoSocial) LIKE :nome")
    List<Fornecedor> findByNomeFantasiaOrRazaoSocial(@Param("nome") String nome);
}
