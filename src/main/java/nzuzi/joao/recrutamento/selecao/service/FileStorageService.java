package nzuzi.joao.recrutamento.selecao.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileStorageService {
    Stream<Path> loadAll();
    String upload(MultipartFile uploadedFile);
    Resource load(String filename);
}
