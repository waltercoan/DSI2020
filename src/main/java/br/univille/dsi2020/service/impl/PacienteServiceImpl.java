package br.univille.dsi2020.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.univille.dsi2020.model.Paciente;
import br.univille.dsi2020.repository.PacienteRepository;
import br.univille.dsi2020.service.PacienteService;

public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void save(Paciente paciente) {
        repository.save(paciente);
    }

    @Override
    public void delete(Paciente paciente) {
        repository.delete(paciente);
    }

    @Override
    public List<Paciente> getAll() {
        return null;
    }
    
}