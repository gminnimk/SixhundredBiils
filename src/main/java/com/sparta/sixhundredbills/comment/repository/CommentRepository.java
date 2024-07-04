package com.sparta.sixhundredbills.comment.repository;

import com.sparta.sixhundredbills.comment.entity.Comment;
import com.sparta.sixhundredbills.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByPost(Post post, Pageable pageable);

    int countLikesByCommentId(Long commentId);
}
