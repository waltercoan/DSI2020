package br.univille.dsi2020.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.univille.dsi2020.model.Documento;
import br.univille.dsi2020.model.ItensDocumento;
import br.univille.dsi2020.repository.DocumentoRepository;
import br.univille.dsi2020.repository.ItensDocumentoRepository;
import br.univille.dsi2020.service.DocumentoService;

@Service
public class DocumentoServiceImpl implements DocumentoService {
    @Autowired
    private DocumentoRepository repository;
    @Autowired
    private ItensDocumentoRepository itensDocumentorepository;
    @Value("${dsi2020.file-upload-dir}")
    private String UPLOAD_DIR;

    @Override
    public void save(Documento documento) {
        repository.save(documento);
    }

    @Override
    public void delete(Documento documento) {
        
        for (ItensDocumento item : documento.getItens()){
            this.deleteItem(item);
        }
        documento.getItens().clear();
        repository.delete(documento);
    }

    @Override
    public List<Documento> getAll() {
        return repository.findAll();
    }

    private void createFileFolder(){
        File file = new File(UPLOAD_DIR);
        if(!file.exists()){
            file.mkdir();
        }
    }

    @Override
    public void uploadFiles(Documento documento, MultipartFile[] files) {
        createFileFolder();
        
        Optional<Documento> oldDocumento = repository.findById(documento.getId());
        if(oldDocumento.isPresent()){
            documento = oldDocumento.get();
        }
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());

                try {
                    Path path = Paths.get(UPLOAD_DIR + fileName);
                    Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    ItensDocumento item = new ItensDocumento();
                    item.setArquivo(fileName);
                    item.setCaminho(path.toString());
                    documento.getItens().add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        repository.save(documento);

    }

    @Override
    public InputStreamResource downloadFile(ItensDocumento item) throws FileNotFoundException {
        createFileFolder();
        File file = new File(item.getCaminho());
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
        return resource;
    }

    
    public void deleteItem(ItensDocumento item) {
        try{
            File file = new File(item.getCaminho());
            boolean sucess = file.delete();
            if (sucess){
                itensDocumentorepository.delete(item);
            }
        }catch(Exception e){

        }
    }
    @Override
    public void deleteItem(Documento documento, ItensDocumento item) {
        try{
            deleteItem(item);
            documento.getItens().remove(item);
            repository.save(documento);
        }catch(Exception e){

        }
    }
    
}
