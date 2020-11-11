package br.univille.dsi2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.dsi2020.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {
    
}
