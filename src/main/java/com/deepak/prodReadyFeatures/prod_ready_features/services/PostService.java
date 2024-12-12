package com.deepak.prodReadyFeatures.prod_ready_features.services;

import com.deepak.prodReadyFeatures.prod_ready_features.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDto> getAllposts();

    PostDto createNewPost(PostDto inputPost);

    PostDto getPostById(Long postId);

    PostDto updatePost(PostDto inputPost, Long postId);
}
