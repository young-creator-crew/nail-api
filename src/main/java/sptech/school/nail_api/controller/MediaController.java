package sptech.school.nail_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sptech.school.nail_api.dto.service.response.MediaResponseDTO;
import sptech.school.nail_api.service.MediaService;

@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaResponseDTO> uploadImage(@RequestParam("media") MultipartFile media){
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.save(media));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
