package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.FilmeHorarioDTO;
import com.tcc.venda_de_ingressos.entity.Horario;
import com.tcc.venda_de_ingressos.entity.HorarioSala;
import com.tcc.venda_de_ingressos.repository.HorarioSalaRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HorarioSalaService {
    @Autowired
    private HorarioSalaRepository repository;

    @Autowired
    private HorarioService horarioService;

    @Autowired
    private SalaService salaService;

    @Autowired
    private FilmeService filmeService;

    public List<HorarioSala> findAll() {
        return this.repository.findAll();
    }

    public HorarioSala findById(UUID id) {
        return this.repository.findById(id).orElse(null);
    }

    public List<HorarioSala> findBySalaAndDisponibilidade(UUID salaId, Boolean disponibildiade) {
        var salaFound = this.salaService.findById(salaId);
        if(salaFound == null)
            return null;

        return this.repository.findBySalaAndDisponibilidade(salaFound, disponibildiade);
    }

    public List<HorarioSala> findBySala(UUID salaId) {
        var salaFound = this.salaService.findById(salaId);
        if(salaFound == null)
            return null;

        return this.repository.findBySala(salaFound);
    }

    public boolean consultDisponibilidade(UUID salaId, UUID horarioId) {
        var sala = this.salaService.findById(salaId);
        var horario = this.horarioService.findById(horarioId);
        if((sala == null) || (horario == null))
            return false;

        var horarioSala = this.repository.consultDisponibilidade(sala, horario);
        return horarioSala.isPresent();
    }

    public HorarioSala create(HorarioSala params) {
        var sala = this.salaService.findById(params.getSala().getId());
        var horario = this.horarioService.findById(params.getHorario().getId());
        if((sala == null) || (horario == null))
            return null;

        return this.repository.save(params);
    }

    public HorarioSala update(HorarioSala params, UUID id) {
        var objectFound = this.findById(id);
        if(objectFound == null)
            return null;

        BeanUtils.copyProperties(params, objectFound);
        return this.repository.save(objectFound);
    }

    public boolean connectHorarioWithFilme(FilmeHorarioDTO filmeHorario) {
        var horarioSala = this.findById(filmeHorario.horarioSala().getId());
        var filme = this.filmeService.findById(filmeHorario.filme().getId());
        if((horarioSala == null) || (filme == null))
            return false;

        this.repository.connectHorarioWithFilme(filmeHorario.filme(), filmeHorario.horarioSala().getSala(), filmeHorario.horarioSala().getHorario());
        return true;
    }

    public boolean updateDisponibilidadeHorario(UUID salaId, Horario horario) {
        var sala = this.salaService.findById(salaId);
        var horarioFound = this.horarioService.findById(horario.getId());
        if((sala == null) || (horarioFound == null))
            return false;

        var disponibilidade = this.consultDisponibilidade(salaId, horario.getId());
        this.repository.updateDisponibilidade(sala, horario, !disponibilidade);
        return true;
    }

    public boolean delete(UUID id) {
        var objectFound = this.findById(id);
        if(objectFound == null)
            return false;

        this.repository.delete(objectFound);
        return true;
    }
}
