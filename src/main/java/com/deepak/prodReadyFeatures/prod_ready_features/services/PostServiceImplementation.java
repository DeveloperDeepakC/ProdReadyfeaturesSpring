package com.deepak.prodReadyFeatures.prod_ready_features.services;

import com.deepak.prodReadyFeatures.prod_ready_features.dto.PostDto;
import com.deepak.prodReadyFeatures.prod_ready_features.entities.PostEntity;
import com.deepak.prodReadyFeatures.prod_ready_features.exceptions.ResourceNotFoundException;
import com.deepak.prodReadyFeatures.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostServiceImplementation(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostDto> getAllposts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity-> modelMapper.map(postEntity,PostDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity postEntity=modelMapper.map(inputPost,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long postId) {
        PostEntity postEntity= postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found with id: "+postId));

        return modelMapper.map(postEntity,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto inputPost, Long postId) {
        PostEntity olderPost= postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post not found with id: "+postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost,olderPost);
        PostEntity savedPostEntity= postRepository.save(olderPost);
        return modelMapper.map(savedPostEntity, PostDto.class);
    }
}
