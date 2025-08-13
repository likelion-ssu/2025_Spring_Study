package likelion.backend.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;


    @PostMapping("/users/{user_id}")
    public ResponseEntity<?> createPost(@PathVariable("user_id") Long memberId, @RequestBody CreatePostRequest request) {
        Long postId = postService.save(request, memberId);
        return ResponseEntity.ok(Map.of("postId", postId));
    }

    @GetMapping("/{post_id}/users/{user_id}")
    public ResponseEntity<?> getPost(@PathVariable("post_id") Long postId, @PathVariable("user_id") Long memberId) {
        PostGetResponse response = postService.get(postId, memberId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{post_id}/users/{user_id}")
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long postId, @PathVariable("user_id") Long memberId, @RequestBody UpdatePostRequest request) {
        postService.update(postId, memberId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{post_id}/users/{user_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") Long postId, @PathVariable("user_id") Long memberId) {
        postService.delete(postId, memberId);
        return ResponseEntity.noContent().build();
    }
}
