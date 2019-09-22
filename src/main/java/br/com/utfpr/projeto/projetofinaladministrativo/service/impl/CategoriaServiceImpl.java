package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Categoria;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.CategoriaRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends CrudServiceImpl<Categoria, Long>
    implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    protected JpaRepository<Categoria, Long> getRepository() {
        return categoriaRepository;
    }
}
