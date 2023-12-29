package com.myCode.termTrain.domain.forum.aplication.command;

import com.myCode.termTrain.domain.forum.core.dto.ForumDto;
import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import com.myCode.termTrain.domain.forum.core.model.Forum;
import com.myCode.termTrain.domain.forum.core.model.Item;
import com.myCode.termTrain.domain.forum.core.service.command.ForumCommandService;
import com.myCode.termTrain.domain.forum.core.service.command.ItemCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class ForumCommandController {
    private final ForumCommandService forumCommandService;
    private final ItemCommandService itemCommandService;

    public ForumCommandController(ForumCommandService forumService, ItemCommandService chatMessageService) {
        this.forumCommandService = forumService;
        this.itemCommandService = chatMessageService;
    }

    @ApiOperation(value = "create new forum question", response = ForumDto[].class, produces = "aplication/json")
    //about this endpoint
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully create"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @PostMapping("/forum")
    public ResponseEntity<Integer> createNewForum(@RequestBody Forum forum) {
        ForumDto forumDto = forumCommandService.createNewForum(forum);
        return ResponseEntity.status(HttpStatus.CREATED).body(forumDto.getId());
    }

    @ApiOperation(value = "add new response message on the forum", response = ForumDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Succesfully added "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @PostMapping("/forum/{id}")
    public ResponseEntity<Integer> createNewItem(@RequestBody Item item) {
        ItemDto messageDto = itemCommandService.createNewItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDto.getId());
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ItemDto sendItem(@Payload Item item) {
        return itemCommandService.createNewItem(item);
    }
}
