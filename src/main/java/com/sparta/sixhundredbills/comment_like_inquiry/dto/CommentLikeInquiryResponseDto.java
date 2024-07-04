package com.sparta.sixhundredbills.comment_like_inquiry.dto;

import com.sparta.sixhundredbills.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentLikeInquiryResponseDto {


    private Comment commentId;
    private int likeCount;



    public CommentLikeInquiryResponseDto(Comment commentId, int likeCount) {
        this.commentId = commentId;
        this.likeCount = likeCount;
    }
}
