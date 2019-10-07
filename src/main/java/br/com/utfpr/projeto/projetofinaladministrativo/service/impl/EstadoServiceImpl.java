package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Estado;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.EstadoRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoServiceImpl extends CrudServiceImpl<Estado, Long>
    implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    protected JpaRepository<Estado, Long> getRepository() {
        return estadoRepository;
    }

    @Override
    public List<Estado> complete(String texto) {
        return estadoRepository.findByNomeLikeIgnoreCase("%" + texto + "%");
    }
}
