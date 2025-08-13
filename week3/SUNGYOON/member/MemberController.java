package likelion.backend.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("api/register")
    ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        memberService.signUp(request);
        return ResponseEntity.ok(Map.of("detail", "회원가입이 성공적으로 처리되었습니다."));
    }

    @PostMapping("api/login")
    ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = memberService.login(request);
        return ResponseEntity.ok(response);
    }
}


//cloient가 보냐면 url을 통해서 controller 찾는다 - 요청의 근원지 찾기
// 이 파일에서 비즈니스 로직 다 돌아가게 하기
// 항목제한하는 경우 <>에 구체적 객체 넣어도 돤더