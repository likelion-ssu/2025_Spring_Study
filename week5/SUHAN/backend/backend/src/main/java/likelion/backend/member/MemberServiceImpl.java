package likelion.backend.member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public void signUp(SignupRequest request) {

        if(request.getLocalId() == null || request.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이 항목은 필수입니다.");
        }

        Member member = Member.builder()
                .localId(request.getLocalId())
                .password(request.getPassword())
                .name(request.getName())
                .age(request.getAge())
                .intro(request.getIntro())
                .build();

        memberRepository.save(member);

    }

    @Override
    public LoginResponse login(LoginRequest request) {
        if(request.getLocalId() == null || request.getPassword() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "필수 항목을 입력하지 않았습니다.");
        }

        Member member = memberRepository.findByLocalId(request.getLocalId())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."));

        if (!member.getPassword().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }
        LoginResponse response = new LoginResponse();
        response.setMemberId(member.getId());
        return response;
    }


}
