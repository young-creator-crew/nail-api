package sptech.school.nail_api.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sptech.school.nail_api.dto.media.MediaRequestDTO;
import sptech.school.nail_api.dto.media.MediaResponseDTO;
import sptech.school.nail_api.service.MediaService;

@RestController
@RequestMapping("/admin/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaResponseDTO> uploadImage(@Valid @ModelAttribute MediaRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(mediaService.save(request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Integer id){
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
