package com.sparta.sixhundredbills.post_like_inquiry.repository;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.post_like.entity.PostLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostLikeInquiryRepository extends JpaRepository<PostLike, Long> {
    Page<PostLike> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}
