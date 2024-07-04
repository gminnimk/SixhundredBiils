package com.sparta.sixhundredbills.post_like.entity;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.post.entity.Post;
import com.sparta.sixhundredbills.timestamp.TimeStamp;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 게시물 좋아요 엔티티 클래스
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostLike extends TimeStamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @MantToOne : 다대일(N:1) 관계를 표현. 예를 들어 여러 개의 댓글이 하나의 게시물에 속하는 경우
    // fetch = FetchType.LAZY : 이 부분은 연관된 엔티티를 로딩할 때 사용할 전략을 설정.
    // LAZY : 지연 로딩을 의미 => 연관된 엔티티를 실제로 사용할 때까지 로딩을 미루는 전략.
    // EAGER : 즉시 로딩을 의미

    // @ManyToOne(fetch = FetchType.LAZY)를 사용하는 이유는 보통 성능 최적화와 데이터 로딩 최적화를 목적
    // 특히 많은 양의 데이터를 다루거나 연관 엔티티가 필요하지 않은 경우에 유용.

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Builder
    public PostLike(User user, Post post) {
        this.user = user;
        this.post = post;
    }
}