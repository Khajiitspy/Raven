package Graveyard.controllers;

import Graveyard.services.FileService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/rich-upload")
    public ResponseEntity<Map<String, String>> uploadRichFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileService.load(file);
        return ResponseEntity.ok(Map.of("fileName", fileName));
    }
}
