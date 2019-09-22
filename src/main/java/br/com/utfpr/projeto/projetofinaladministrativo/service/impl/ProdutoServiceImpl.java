package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.ProdutoRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long>
    implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    protected JpaRepository<Produto, Long> getRepository() {
        return produtoRepository;
    }
}
