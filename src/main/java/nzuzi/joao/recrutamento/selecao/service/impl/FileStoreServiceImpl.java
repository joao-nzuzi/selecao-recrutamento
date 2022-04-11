package nzuzi.joao.recrutamento.selecao.service.impl;

import nzuzi.joao.recrutamento.selecao.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.stream.Stream;

@Service
public class FileStoreServiceImpl implements FileStorageService {

    private Path pathLocation = null;
    final String SEPARATOR = File.separator;
    Logger logger = LoggerFactory.getLogger(FileStoreServiceImpl.class);

    final String ROOT_PATH = System.getProperty("user.home").concat(SEPARATOR)
            .concat("Documents").concat(SEPARATOR)
            .concat("Meus projectos").concat(SEPARATOR)
            .concat("recrutamento-selecao").concat(SEPARATOR)
            .concat("selecao-recrutamento").concat(SEPARATOR).concat("uploads");

    private void setLocationPath(String path) {
        try {
            pathLocation = Paths.get(path);
            if (!pathLocation.toFile().exists()) {
                new File(path).mkdirs();
            }
        } catch (Exception e) {
            logger.error("Diretório não existe", e.getCause());
        }
    }

    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), pathLocation.resolve(file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException fileAlreadyExistsException) {
            logger.info(String.valueOf(fileAlreadyExistsException));
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao fazer upload do ficheiro", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ocorreu um erro ao fazer upload do ficheiro!");
        }
    }

    @Override
    public String upload(MultipartFile file) {
        setLocationPath(ROOT_PATH);
        save(file);
        return returnFilePath(ROOT_PATH, file.getOriginalFilename(), false);
    }

    private String returnFilePath(String caminho, String nome, Boolean isZip) {
        return isZip ? getZipFile(caminho) : caminho.concat(SEPARATOR).concat(nome);
    }

    private String getZipFile(String path) {
        return path.replace("documentos", "").concat("Documentos.zip");
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(Paths.get(this.ROOT_PATH), 1).filter(path -> !path.equals(this.ROOT_PATH)).map(Paths.get(this.ROOT_PATH)::relativize);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível carregar o ficheiro!");
        }
    }

    @Override
    public Resource load(String filename) {
        try {
            Path file = pathLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível ler o ficheiro!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Erro: ".concat(e.getMessage()));
        }
    }
}
