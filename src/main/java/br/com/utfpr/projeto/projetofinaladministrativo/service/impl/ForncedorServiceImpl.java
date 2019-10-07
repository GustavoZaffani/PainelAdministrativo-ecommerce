package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Fornecedor;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.FornecedorRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ForncedorServiceImpl extends CrudServiceImpl<Fornecedor, Long>
    implements FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    protected JpaRepository<Fornecedor, Long> getRepository() {
        return fornecedorRepository;
    }

    @Override
    public List<Fornecedor> complete(String texto) {
        return fornecedorRepository.findByNomeFantasiaOrRazaoSocial("%" + texto.toUpperCase() + "%");
    }
}
