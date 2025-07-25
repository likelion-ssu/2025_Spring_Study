package likelion.backend.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String localId;
    private String password;
    private String name;
    private Long age;
    private String intro;
}
