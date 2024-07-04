package com.sparta.sixhundredbills.post_like_inquiry.service;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.post_like_inquiry.dto.PostLikeInquiryResponseDto;
import com.sparta.sixhundredbills.post_like_inquiry.repository.PostLikeInquiryRepository;
import com.sparta.sixhundredbills.post_like.repository.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeInquiryService {

    private final PostLikeInquiryRepository postLikeInquiryRepository;
    private final PostLikeRepository postLikeRepository;

    public Page<PostLikeInquiryResponseDto> getLikedPosts(User user, Pageable pageable) {
        return postLikeInquiryRepository.findByUserOrderByCreatedAtDesc(user, pageable)
                .map(postLike -> {
                    int likeCount = postLikeRepository.countByPostId(postLike.getPost().getId());
                    return new PostLikeInquiryResponseDto(postLike.getPost(), likeCount);
                });
    }
}
