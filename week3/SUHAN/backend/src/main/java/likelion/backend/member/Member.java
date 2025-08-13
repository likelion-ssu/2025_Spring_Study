package likelion.backend.member;

import jakarta.persistence.*;
import likelion.backend.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column
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

}
