package br.com.utfpr.projeto.projetofinaladministrativo.service;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Estado;

import java.util.List;

public interface EstadoService extends CrudService<Estado, Long> {

    List<Estado> complete(String texto);
}
