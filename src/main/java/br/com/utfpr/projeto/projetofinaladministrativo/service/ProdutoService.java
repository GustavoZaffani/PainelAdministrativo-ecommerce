package br.com.utfpr.projeto.projetofinaladministrativo.service;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;

import java.util.List;

public interface ProdutoService extends CrudService<Produto, Long> {

    List<Produto> complete(String nome);

    List<Produto> findByTipoEquals(String tipo);

    List<Produto> findByTipoEqualsAndCategoriaEquals(String tipo, Long idCarrinho);

    List<Object[]> findProdutosMaisVendidos();

    List<Object[]> findProdutosMaisComprados();
}
