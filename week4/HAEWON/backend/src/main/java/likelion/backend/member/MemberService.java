package likelion.backend.member;

public interface MemberService {
    void signUp(SignUpRequest request);
    LoginResponse login(LoginRequest request);
}
