package com.myCode.termTrain.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.myCode.termTrain.model.Forum;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.myCode.termTrain.dto.ForumDto;
import com.myCode.termTrain.repository.ForumRepository;

@Service
public class ForumService {
    
    private final ForumRepository forumRepository;
    private final ModelMapper modelMapper;

    

    public ForumService(ForumRepository forumRepository, ModelMapper modelMapper) {
        this.forumRepository = forumRepository;
        this.modelMapper = modelMapper;
    }

    private Function<? super Forum, ? extends ForumDto> convertForumToDto(){
        return forum -> modelMapper.map(forum, ForumDto.class);
    }

    public List<ForumDto> findAll(){
        List<Forum> forum = forumRepository.findAll();        
        return forum.stream().map(convertForumToDto()).collect(Collectors.toList());
    }
    
    public ForumDto findById(Integer id){
        Forum forum = forumRepository.findById(id).get();        
        return modelMapper.map(forum, ForumDto.class);
    }

    public Integer save(Forum forum){
        Forum forumTemp = forumRepository.saveAndFlush(forum);        
        return forumTemp.getId();
    }

    public void delete(Forum forum){
        forumRepository.delete(forum);        
    }
}
