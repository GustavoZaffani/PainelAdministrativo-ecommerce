package br.com.utfpr.projeto.projetofinaladministrativo.service;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Produto;

import java.util.List;

public interface ProdutoService extends CrudService<Produto, Long> {

    List<Produto> complete(String nome);
}
