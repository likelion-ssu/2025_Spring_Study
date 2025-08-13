package likelion.backend.post;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdatePostRequest {
    private String title;
    private String content;
}

//put으롯 수정하려면 원래 id까지 다 있어야함