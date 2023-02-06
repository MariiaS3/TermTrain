package com.myCode.termTrain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ForumDto", description = "questions on Forum")
public class ForumDto {

    @ApiModelProperty(readOnly = true, value = "Integer", dataType = "Integer", example = "1", notes = "The database generated Integer forum question", required = true)
    private Integer id;

    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "user@gmail.com", notes = "Name of user who crete question", required = true)
    private String username;

    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "First question on forum", notes = "Question on forum", required = true)
    private String title;

}
