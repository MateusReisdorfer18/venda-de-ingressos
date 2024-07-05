package com.tcc.venda_de_ingressos.service;

import com.tcc.venda_de_ingressos.entity.*;
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

    @Autowired
    private AssentoService assentoService;

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

        int tamanho = 0;
        switch (sala.getTamanho().toString()) {
            case "GRANDE":
                tamanho = 50;
            case "MEDIA":
                tamanho = 35;
            case "PEQUENA":
                tamanho = 20;
        }

        if(tamanho == 0)
            return null;

        for(int i=0; i<tamanho; i++) {
            var assento = new Assento();
            assento.setNome(sala.getNome() + (i+1));
            this.assentoService.create(assento);
            params.getAssentos().add(assento);
        }

        return this.repository.save(params);
    }

    public HorarioSala update(HorarioSala params, UUID id) {
        var objectFound = this.findById(id);
        if(objectFound == null)
            return null;

        BeanUtils.copyProperties(params, objectFound);
        return this.repository.save(objectFound);
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
