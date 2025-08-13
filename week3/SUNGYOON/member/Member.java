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

//member는 1에 해당해서 post여러개를 가짐 - list를 만들기
//mappedby 는 내가 주인이라는 의미이다

// 쿼리 보내는 거랑 jpa를 통해 하는 것과 내부 방식 이 다름 - cascade 설정해주기
//m:n은 죽이고싶어서 죽이지못함
//부모가 죽으면 자식들을 죽인다