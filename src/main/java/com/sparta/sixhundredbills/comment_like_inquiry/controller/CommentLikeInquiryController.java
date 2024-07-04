package com.sparta.sixhundredbills.comment_like_inquiry.controller;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.comment_like_inquiry.dto.CommentLikeInquiryResponseDto;
import com.sparta.sixhundredbills.comment_like_inquiry.service.CommentLikeInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentLikeInquiryController {

    private final CommentLikeInquiryService commentLikeInquiryService;

    @GetMapping("/liked")
    public ResponseEntity<Page<CommentLikeInquiryResponseDto>> getLikedComments(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "userId") Long userId
    ) {
        Pageable pageable = PageRequest.of(page, size);
        User user = new User(); // 실제 세션 등에서 사용자 정보를 받아와야 함

        Page<CommentLikeInquiryResponseDto> likedComments = commentLikeInquiryService.getLikedComments(userId, pageable);
        return ResponseEntity.ok(likedComments);
    }
}
