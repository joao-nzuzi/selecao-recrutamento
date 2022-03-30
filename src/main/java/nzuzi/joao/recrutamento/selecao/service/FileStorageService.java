package nzuzi.joao.recrutamento.selecao.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    void init();
    void save(MultipartFile file);
    Resource load(String filename);
}
