package com.term_train.service;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import com.term_train.ddd.forum.domain.ForumFacade;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.term_train.ddd.forum.domain.dto.ForumDto;
import com.term_train.ddd.forum.domain.model.Forum;
import com.term_train.ddd.forum.infrastructure.port.ForumRepository;

@ExtendWith(MockitoExtension.class)
public class ForumServiceTest {
    
    @InjectMocks
    private ForumFacade forumFacade;
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
        
        List<ForumDto> forumDtos = forumFacade.findAllForums();
        assertThat(forumDtos.size()).isEqualTo(1);
    }

    private Forum getForum(){
        return Forum.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }

    private ForumDto getForumDto(){
        return ForumDto.builder().id(1).username("test@gmail.com").title("test forumitem").build();
    }
}
