package br.univille.dsi2020.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import br.univille.dsi2020.model.Documento;
import br.univille.dsi2020.model.ItensDocumento;
import br.univille.dsi2020.service.DocumentoService;

@Controller
@RequestMapping({"/documento"})
public class DocumentoController {


    @Autowired
    private DocumentoService service;
    
    @GetMapping({""})
    public ModelAndView index(){
        List<Documento> lista = service.getAll();
        return new ModelAndView("documento/index","listadocumentos",lista);
    }   

    @GetMapping("/novo")
    public ModelAndView novo(@ModelAttribute Documento documento){
        return new ModelAndView("documento/form");
    }
    @GetMapping(value="/alterar/{id}")
    public ModelAndView edit(@PathVariable("id") Documento documento){
        return new ModelAndView("documento/form","documento",documento);
    }
    @GetMapping(value="/detalhar/{id}")
    public ModelAndView detail(@PathVariable("id") Documento documento){
        HashMap<String,Object> dados = new HashMap();
        dados.put("documento",documento);
        dados.put("detalhar",true);
        return new ModelAndView("documento/form",dados);
    }

    

    @GetMapping(value="/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Documento documento){
        service.delete(documento);
        return new ModelAndView("redirect:/documento");
    }

    @GetMapping(value="/delete/{id}/deleteitem/{iditem}")
    public ModelAndView delete(@PathVariable("id") Documento documento, @PathVariable("iditem") ItensDocumento item){
        service.deleteItem(documento,item);
        return new ModelAndView("redirect:/documento");
    }

    @PostMapping(params={"form"})
    public ModelAndView save(Documento documento, @RequestParam("files") MultipartFile[] files){

        service.uploadFiles(documento, files);

        return new ModelAndView("redirect:/documento");
    }


    @RequestMapping(path = "/download/{id}", method = RequestMethod.GET)
    public ResponseEntity<Resource> download(@PathVariable("id") ItensDocumento item) throws IOException {
        
        InputStreamResource resource = service.downloadFile(item);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + item.getArquivo())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }


}
