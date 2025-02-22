package com.sparta.sixhundredbills.post_like_inquiry.controller;

import com.sparta.sixhundredbills.auth.security.UserDetailsImpl;
import com.sparta.sixhundredbills.post_like_inquiry.dto.PostLikeInquiryResponseDto;
import com.sparta.sixhundredbills.post_like_inquiry.service.PostLikeInquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostLikeInquiryController {

    private final PostLikeInquiryService postLikeInquiryService;

    @GetMapping("/likes")
    public Page<PostLikeInquiryResponseDto> getLikedPosts(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return postLikeInquiryService.getLikedPosts(userDetails.getUser(), pageable);
    }
}