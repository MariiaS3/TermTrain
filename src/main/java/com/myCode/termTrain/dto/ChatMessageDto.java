package com.myCode.termTrain.dto;

import com.myCode.termTrain.model.Forum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "ChatMessageDto", description = "Message response on Forum")
public class ChatMessageDto {
 
    @ApiModelProperty(readOnly = true, value = "Integer", dataType = "Integer", example = "1", notes = "The database generated Integer forum response message", required = true)
    private Integer id;

    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "user@gmail.com", notes = "Name of user who crete response message", required = true)
    private String username;

    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "First response message on forum", notes = "Response message on forum", required = true)
    private String message;

    @ApiModelProperty(readOnly = true, value = "Integer", dataType = "Integer", example = "1", notes = "Id forum on which user response", required = true)
    private Forum forum;

}
