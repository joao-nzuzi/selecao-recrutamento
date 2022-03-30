package nzuzi.joao.recrutamento.selecao.controller;

import nzuzi.joao.recrutamento.selecao.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/selecao/recrutamento/candidatos")
public class FileController {

    @Autowired
    FileStorageService service;

    @PostMapping("/ficheiros")
    public ResponseEntity<?> uploadFile(@RequestParam("ficheiro") MultipartFile file){
        try {
            service.save(file);
            return ResponseEntity.status(HttpStatus.OK).body("Ficheiro carregado com sucesso: ".concat(file.getOriginalFilename()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Ocorreu um erro a fazer upload do ficheiro: ".concat(file.getOriginalFilename()).concat("!"));
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = service.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"".concat(file.getFilename()).concat("\"")).body(file);
    }
}
