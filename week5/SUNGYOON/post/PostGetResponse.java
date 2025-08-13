package likelion.backend.post;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PostGetResponse {
    private Long diaryId;
    private String title;
    private String content;
    private Long userId;
}
