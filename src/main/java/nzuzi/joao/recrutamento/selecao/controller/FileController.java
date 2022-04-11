package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.entity.FileInfo;
import nzuzi.joao.recrutamento.selecao.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/ficheiros")
public class FileController {

    @Autowired
    FileStorageService service;
    ResponseEntity<String> retorno;

    @PostMapping("/")
    public ResponseEntity<?> uploadFile(@RequestParam("ficheiro") MultipartFile file){
        try {
            if(file.isEmpty()){
                retorno = ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Por favor, informa o ficheiro que pretende carregar");
            }else{
                service.upload(file);
                return ResponseEntity.status(HttpStatus.OK).body("Ficheiro ".concat(file.getOriginalFilename().concat(" Salvo Com Sucesso!")));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Ocorreu um erro a fazer upload do ficheiro: ".concat(file.getOriginalFilename()).concat("!"));
        }
        return retorno;
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = service.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = service.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
