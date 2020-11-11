package br.univille.dsi2020.service;

import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;

import br.univille.dsi2020.model.Documento;
import br.univille.dsi2020.model.ItensDocumento;

public interface DocumentoService {
    void save(Documento documento);
    void delete(Documento documento);
    List<Documento> getAll();
    void uploadFiles(Documento documento, MultipartFile[] files);
    InputStreamResource downloadFile(ItensDocumento item) throws FileNotFoundException;
    void deleteItem(Documento documento, ItensDocumento item);
    void deleteItem(ItensDocumento item);
}
