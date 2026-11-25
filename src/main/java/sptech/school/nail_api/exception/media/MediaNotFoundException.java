package sptech.school.nail_api.exception.media;

public class MediaNotFoundException extends RuntimeException {
    public MediaNotFoundException(Integer id) {
        super("No media exists with this id: " + id);
    }
}
