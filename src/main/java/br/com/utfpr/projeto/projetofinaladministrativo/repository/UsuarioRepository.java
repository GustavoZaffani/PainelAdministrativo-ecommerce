package br.com.utfpr.projeto.projetofinaladministrativo.repository;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}
