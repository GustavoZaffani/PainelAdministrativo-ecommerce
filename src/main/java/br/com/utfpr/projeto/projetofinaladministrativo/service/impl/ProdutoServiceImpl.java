package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.ProdutoRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoServiceImpl extends CrudServiceImpl<Produto, Long>
    implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    protected JpaRepository<Produto, Long> getRepository() {
        return produtoRepository;
    }

    @Override
    public List<Produto> complete(String nome) {
        return produtoRepository.findByNomeLikeIgnoreCase("%" + nome + "%");
    }

    @Override
    public List<Produto> findByTipoEquals(String tipo) {
        return produtoRepository.findByTipoEquals(tipo);
    }

    @Override
    public List<Produto> findByTipoEqualsAndCategoriaEquals(String tipo, Long idCarrinho) {
        return produtoRepository.findByTipoEqualsAndCategoriaIdEquals("%" + tipo + "%", idCarrinho);
    }

    @Override
    public List<Object[]> findProdutosMaisVendidos() {
        return produtoRepository.findProdutosMaisVendidos();
    }

    @Override
    public List<Object[]> findProdutosMaisComprados() {
        return produtoRepository.findProdutosMaisComprados();
    }
}
