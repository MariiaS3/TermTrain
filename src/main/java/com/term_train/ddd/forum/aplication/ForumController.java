package com.term_train.ddd.forum.aplication;

import com.term_train.ddd.forum.domain.ForumFacade;
import com.term_train.ddd.forum.domain.ItemFacade;
import com.term_train.ddd.forum.domain.dto.ForumDto;
import com.term_train.ddd.forum.domain.dto.ItemDto;
import com.term_train.ddd.forum.domain.model.Forum;
import com.term_train.ddd.forum.domain.model.Item;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class ForumController {
    private final ForumFacade forumFacade;
    private final ItemFacade itemFacade;

    public ForumController(ForumFacade forumService, ItemFacade chatMessageService) {
        this.forumFacade = forumService;
        this.itemFacade = chatMessageService;
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
        ForumDto forumDto = forumFacade.createNewForum(forum);
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
        ItemDto messageDto = itemFacade.createNewItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(messageDto.getId());
    }

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ItemDto sendItem(@Payload Item item) {
        return itemFacade.createNewItem(item);
    }


    @ApiOperation(value = "return list of all forum questions", response = ForumDto[].class, produces = "aplication/json")
    //about this endpoint
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/forum")
    public ResponseEntity<List<ForumDto>> getListOfForum() {
        List<ForumDto> forumDtos = forumFacade.findAllForums();
        return ResponseEntity.ok(forumDtos);
    }

    @ApiOperation(value = "return list of all forum response messages", response = ForumDto[].class, produces = "aplication/json")
    //about this endpoint
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved "),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/forum/{id}")
    public ResponseEntity<List<ItemDto>> getListOfItems(@PathVariable("id") Integer id) {
        List<ItemDto> chatMessages = itemFacade.findAllItemsByForumId(id);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }
}
