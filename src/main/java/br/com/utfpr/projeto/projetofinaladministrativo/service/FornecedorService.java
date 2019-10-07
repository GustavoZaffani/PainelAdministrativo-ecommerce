package br.com.utfpr.projeto.projetofinaladministrativo.service;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Fornecedor;

import java.util.List;

public interface FornecedorService extends CrudService<Fornecedor, Long> {

    List<Fornecedor> complete(String texto);
}
