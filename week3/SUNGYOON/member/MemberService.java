package likelion.backend.member;


public interface MemberService {
    void signUp(SignupRequest request);
    LoginResponse login(LoginRequest request);
}