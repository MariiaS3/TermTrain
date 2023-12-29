package com.myCode.termTrain.domain.forum.aplication.query;

import com.myCode.termTrain.domain.forum.core.dto.ForumDto;
import com.myCode.termTrain.domain.forum.core.dto.ItemDto;
import com.myCode.termTrain.domain.forum.core.service.query.ForumQueryService;
import com.myCode.termTrain.domain.forum.core.service.query.ItemQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class ForumQueryController {
    private final ForumQueryService forumQueryService;
    private final ItemQueryService itemQueryService;

    public ForumQueryController(ForumQueryService forumService, ItemQueryService itemQueryService) {
        this.forumQueryService = forumService;
        this.itemQueryService = itemQueryService;
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
        List<ForumDto> forumDtos = forumQueryService.findAllForums();
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
        List<ItemDto> chatMessages = itemQueryService.findAllItemsByForumId(id);
        return new ResponseEntity<>(chatMessages, HttpStatus.OK);
    }

}
