package com.myCode.termTrain.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myCode.termTrain.dto.ChatMessageDto;
import com.myCode.termTrain.dto.ForumDto;
import com.myCode.termTrain.model.ChatMessage;
import com.myCode.termTrain.model.Forum;
import com.myCode.termTrain.service.ChatMessageService;
import com.myCode.termTrain.service.ForumService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "TermTrain Api", tags = "TermTrain Api",produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class ForumController {
    
    private final ForumService forumService;
    private final ModelMapper modelMapper;
    private final ChatMessageService chatMessageService;

    public ForumController(ForumService forumService, ModelMapper modelMapper, ChatMessageService chatMessageService) {
        this.forumService = forumService;
        this.modelMapper = modelMapper;
        this.chatMessageService = chatMessageService;
    }

    @ApiOperation(value = "returnlist of all forum questions", response =  ForumDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 200 , message = "Succesfully retrieved "),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/forum")
    public ResponseEntity<List<ForumDto>> getForum(){
        List<ForumDto> forumDtos = forumService.findAll();
        return ResponseEntity.ok(forumDtos);
    }

    @ApiOperation(value = "create new forum question", response =  ForumDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 201 , message = "Succesfully create"),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @PostMapping("/forum")
    public ResponseEntity<Integer> addForum(@RequestBody Forum forum){
        Integer id = forumService.save(forum);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @ApiOperation(value = "add new response message on the forum", response =  ForumDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 201 , message = "Succesfully added "),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @PostMapping("/forum/{id}")
    public ResponseEntity<Integer> addChatMessage(@RequestBody ChatMessage chatMessage){
        Integer id = chatMessageService.save(chatMessage);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @ApiOperation(value = "returnlist of all forum response messages", response =  ForumDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 200 , message = "Succesfully retrieved "),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/forum/{id}")
    public ResponseEntity<?> getChatMessange(@PathVariable("id") Integer id){        
        List<ChatMessageDto> chatMessages = chatMessageService.findByForumId(id);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }
    
    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessageDto sendMessage(@Payload ChatMessage chatMessage){
        chatMessageService.save(chatMessage);
        return modelMapper.map(chatMessage, ChatMessageDto.class);
    }   
}
