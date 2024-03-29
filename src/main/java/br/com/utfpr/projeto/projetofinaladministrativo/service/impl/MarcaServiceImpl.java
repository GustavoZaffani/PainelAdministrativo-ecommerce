package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Marca;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.MarcaRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class MarcaServiceImpl extends CrudServiceImpl<Marca, Long>
    implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    protected JpaRepository<Marca, Long> getRepository() {
        return marcaRepository;
    }
}
