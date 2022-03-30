package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.service.FileStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStoreServiceImpl implements FileStorageService {

    private final Path root = Paths.get("uploads");

    @Override
    @PostConstruct
    public void init() {
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível inicializar a pasta para upload!");
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Path dfile = this.root.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), dfile, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException("Ocorreu um erro ao fazer upload do ficheiro. Erro: " + e.getMessage());
        }
    }

    @Override
    public Resource load(String filename) {

        try {
            Path file = root.resolve(filename).normalize();
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Ocorreu um erro ao ler o ficheiro!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro: " + e.getMessage());
        }
    }
}
