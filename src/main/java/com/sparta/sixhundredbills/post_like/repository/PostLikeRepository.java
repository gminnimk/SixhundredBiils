package com.sparta.sixhundredbills.post_like.repository;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.post.entity.Post;
import com.sparta.sixhundredbills.post_like.entity.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * 게시물 좋아요 레포지토리 인터페이스
 */
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    // 사용자와 게시물로 좋아요 찾기
    Optional<PostLike> findByUserAndPost(User user, Post post);

    //postlike 투플 갯수 카운트 쿼리(좋아요 갯수)
    @Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post.id = :postId")
    int countByPostId(Long postId);




    // 해당 postId에 해당하는 게시물의 likeCount를 증가시키는 쿼리. 이 메서드는 좋아요를 추가할 때 호출.
    // 좋아요를 추가하거나 취소할 때마다 Post 엔티티의 likeCount 값을 업데이트할 수 있음.
    // 이후 PostLikeInquiryService에서는 PostLikeRepository를 사용하여 좋아요 수가 포함된 데이터를 조회하여 클라이언트에 반환.

    @Transactional // 트랙잭션 내에서 메서드가 실행됨을 표시.
    @Modifying // 이 메서드가 DB 수정하는 쿼리임을 나타냄.
    @Query("UPDATE Post p SET p.likeCount = p.likeCount + 1 WHERE p.id = :postId") // Post 엔티티의 likeCount 값을 증가하는 역할
    void incrementLikeCount(Long postId); // likeCount 를 1 증가시키는 메서드 정의

    @Transactional
    @Modifying
    @Query("UPDATE Post p SET p.likeCount = p.likeCount - 1 WHERE p.id = :postId")
    void decrementLikeCount(Long postId);

    @Transactional
    @Modifying
    @Query("DELETE FROM PostLike pl WHERE pl.post.id = :postId")
    void deleteByPostId(Long postId);
}
