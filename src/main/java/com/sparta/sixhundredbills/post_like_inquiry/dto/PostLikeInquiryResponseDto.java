package com.sparta.sixhundredbills.post_like_inquiry.dto;

import com.sparta.sixhundredbills.post.entity.Post;
import lombok.Getter;

@Getter
public class PostLikeInquiryResponseDto {

    private Long postId;
    private Long userId;
    private String content;
    private int likeCount;

    public PostLikeInquiryResponseDto(Post post, int likeCount) {
        this.postId = post.getId();
        this.content = post.getContent();
        this.userId = post.getUser().getId();
        this.likeCount = post.getLikeCount();
    }
}
