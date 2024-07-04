package com.sparta.sixhundredbills.comment_like_inquiry.repository;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.comment_like.entity.CommentLike;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeInquiryRepository extends JpaRepository<CommentLike, Long> {
    Page<CommentLike> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
}

