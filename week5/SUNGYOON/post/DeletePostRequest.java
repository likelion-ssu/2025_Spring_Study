package likelion.backend.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DeletePostRequest {
    private String title;
    private String content;
}
