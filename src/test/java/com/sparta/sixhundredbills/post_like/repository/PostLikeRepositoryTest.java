package com.sparta.sixhundredbills.post_like.repository;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.post.entity.Post;
import com.sparta.sixhundredbills.post_like.entity.PostLike;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PostLikeRepositoryTest {

    @Autowired
    private PostLikeRepository postLikeRepository;

    @Test
    public void testFindByUserAndPost() {
        // given
        User user = new User(); // 적절한 User 객체 생성
        Post post = new Post(); // 적절한 Post 객체 생성
        post.setId(1L);

        PostLike postLike = new PostLike();
        postLike.setUser(user);
        postLike.setPost(post);
        postLikeRepository.save(postLike);

        // when
        Optional<PostLike> foundPostLike = postLikeRepository.findByUserAndPost(user, post);

        // then
        assertThat(foundPostLike).isPresent();
        assertThat(foundPostLike.get().getPost()).isEqualTo(post);
    }
}
