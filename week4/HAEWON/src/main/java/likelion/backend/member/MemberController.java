package likelion.backend.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @PostMapping("/register")
    ResponseEntity<?> createMember(@RequestBody SignUpRequest request){
        if(request.getLocalId().isEmpty() || request.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("detail", "이 필드는 필수 항목입니다."));
        }
        memberService.signUp(request);

        return ResponseEntity.ok(Map.of("detail", "회원가입이 성공적으로 처리되었습니다."));
    }

    @PostMapping("/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }
}
