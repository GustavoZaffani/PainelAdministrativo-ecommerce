package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Permissao;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.PermissaoRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissaoServiceImpl extends CrudServiceImpl<Permissao, Integer>
    implements PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    protected JpaRepository<Permissao, Integer> getRepository() {
        return permissaoRepository;
    }
}
