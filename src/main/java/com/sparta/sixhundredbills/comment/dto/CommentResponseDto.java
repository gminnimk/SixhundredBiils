package com.sparta.sixhundredbills.comment.dto;


import com.sparta.sixhundredbills.comment.entity.Comment;

import lombok.Builder;
import lombok.Getter;

@Getter



public class CommentResponseDto {
    private String showName; // 익명으로 표시될 이름
    private String comment;
    private int likeCount;

    @Builder
    public CommentResponseDto(String showName, String comment, int likeCount) {
        this.showName = showName;
        this.comment = comment;
        this.likeCount = likeCount;
    }

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getComment();
        this.likeCount = comment.getLikeCount();
    }

    public CommentResponseDto(Comment comment, int likeCount) {
        this.comment = comment.getComment();
        this.likeCount = likeCount;
    }
}


