package br.univille.dsi2020.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.univille.dsi2020.model.Paciente;

@Service
public interface PacienteService {
    void save(Paciente paciente);
    void delete(Paciente paciente);
    List<Paciente> getAll();
}