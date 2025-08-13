package likelion.backend.member;

import jakarta.persistence.*;
import likelion.backend.post.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false)
    private String localId;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private Long age;

    @Column
    private String intro;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String localId, String password, String name, Long age, String intro) {
        this.localId = localId;
        this.password = password;
        this.name = name;
        this.age = age;
        this.intro = intro;
    }

}
