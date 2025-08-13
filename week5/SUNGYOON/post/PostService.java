package likelion.backend.post;

import likelion.backend.member.Member;
import likelion.backend.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public Long save(CreatePostRequest request, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."));

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .member(member)
                .build();
        postRepository.save(post);
        return post.getId();
    }

    public PostGetResponse get(Long postId, Long memberId) {
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));

        PostGetResponse response = new PostGetResponse();
        response.setDiaryId(post.getId());
        response.setTitle(post.getTitle());
        response.setContent(post.getContent());
        response.setUserId(memberId);
        return response;
    }

    public void update(Long postId, Long memberId, UpdatePostRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."));
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));

        if (!post.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글을 수정할 권한이 없습니다.");
        }

        post.update(request.getTitle(), request.getContent());

    }

    public void delete(Long postId, Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."));
        Post post = postRepository.findByPostId(postId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다."));

        if (!post.getMember().getId().equals(memberId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "게시글을 삭제할 권한이 없습니다.");
        }

        postRepository.delete(post);

    }
}
