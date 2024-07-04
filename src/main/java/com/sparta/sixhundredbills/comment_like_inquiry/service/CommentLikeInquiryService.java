package com.sparta.sixhundredbills.comment_like_inquiry.service;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.auth.repository.UserRepository;
import com.sparta.sixhundredbills.comment.entity.Comment;
import com.sparta.sixhundredbills.comment.repository.CommentRepository;
import com.sparta.sixhundredbills.comment_like.entity.CommentLike;
import com.sparta.sixhundredbills.comment_like.repository.CommentLikeRepository;
import com.sparta.sixhundredbills.comment_like_inquiry.dto.CommentLikeInquiryResponseDto;
import com.sparta.sixhundredbills.comment_like_inquiry.repository.CommentLikeInquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeInquiryService {

    private final CommentLikeInquiryRepository commentLikeInquiryRepository;
    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<CommentLikeInquiryResponseDto> getLikedComments(Long userId, Pageable pageable) {
        // 사용자 ID를 기반으로 사용자 정보 조회
        User user = getUserById(userId);

        // 사용자가 좋아요한 댓글 목록 조회
        Page<CommentLike> commentLikes = commentLikeInquiryRepository.findByUserOrderByCreatedAtDesc(user, pageable);

        // CommentLike 엔티티를 CommentLikeInquiryResponseDto로 변환
        return commentLikes.map(commentLike -> {
            Comment comment = commentLike.getComment();
            // 좋아요 수를 조회해서 DTO에 설정
            int likeCount = commentLikeRepository.countByCommentId(comment.getId());
            return new CommentLikeInquiryResponseDto(comment, likeCount);
        });
    }

    // 사용자 ID를 기반으로 사용자 정보 조회 메서드
    private User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }
}
