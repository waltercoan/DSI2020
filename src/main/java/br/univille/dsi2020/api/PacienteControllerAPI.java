package br.univille.dsi2020.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.univille.dsi2020.model.Paciente;
import br.univille.dsi2020.service.PacienteService;

@RestController
@RequestMapping("/api/v1/pacientes")
public class PacienteControllerAPI {
    @Autowired
    private PacienteService service;
 
    @GetMapping
    public ResponseEntity<List<Paciente>> getAll(){
        List<Paciente> lista = service.getAll();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")    
    public ResponseEntity<Paciente> getById(@PathVariable("id") Paciente paciente){
        return new ResponseEntity<>(paciente, HttpStatus.OK);
    }

}