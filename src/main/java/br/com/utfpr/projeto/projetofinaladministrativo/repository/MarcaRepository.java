package br.com.utfpr.projeto.projetofinaladministrativo.repository;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
