package br.com.utfpr.projeto.projetofinaladministrativo.service.impl;

import br.com.utfpr.projeto.projetofinaladministrativo.model.Cidade;
import br.com.utfpr.projeto.projetofinaladministrativo.repository.CidadeRepository;
import br.com.utfpr.projeto.projetofinaladministrativo.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeServiceImpl extends CrudServiceImpl<Cidade, Long>
    implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    protected JpaRepository<Cidade, Long> getRepository() {
        return cidadeRepository;
    }

    @Override
    public List<Cidade> complete(String texto, Long idEstado) {
        return cidadeRepository.findByNomeLikeIgnoreCaseAndEstadoId("%" + texto + "%", idEstado);
    }
}
