package com.myCode.termTrain.service;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.myCode.termTrain.dto.ForumDto;
import com.myCode.termTrain.model.Forum;
import com.myCode.termTrain.repository.ForumRepository;

@ExtendWith(MockitoExtension.class)
public class ForumServiceTest {
    
    @InjectMocks
    private ForumService forumService;

    @Mock
    private ForumRepository forumRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void shouldReturnListOfAllForumItems(){
        List<Forum> items = new ArrayList<>();
        Forum forum = getForum();
        items.add(getForum());
        ForumDto forumDto = getForumDto();

        when(forumRepository.findAll()).thenReturn(items);
        when(modelMapper.map(forum, ForumDto.class)).thenReturn(forumDto);
        
        List<ForumDto> forumDtos = forumService.findAll();
        assertThat(forumDtos.size()).isEqualTo(1);
    }

    private Forum getForum(){
        return Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }

    private ForumDto getForumDto(){
        return ForumDto.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }
}
