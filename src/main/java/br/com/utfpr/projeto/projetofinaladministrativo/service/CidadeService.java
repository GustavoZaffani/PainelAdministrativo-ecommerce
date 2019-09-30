package br.com.utfpr.projeto.projetofinaladministrativo.service;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Cidade;

import java.util.List;

public interface CidadeService extends CrudService<Cidade, Long> {

    List<Cidade> complete(String texto, Long idEstado);
}
