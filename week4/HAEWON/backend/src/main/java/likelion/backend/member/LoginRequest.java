package likelion.backend.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String LocalId;
    private String password;
}
