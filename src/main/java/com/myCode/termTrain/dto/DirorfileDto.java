package com.myCode.termTrain.dto;

import java.util.UUID;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "DirFileDto", description = "all details about directory and files if it is not directory")
public class DirorfileDto {
    
    @ApiModelProperty(readOnly = true, value = "UUID", dataType = "UUID", example = "b5607d38-8fc1-43ef-b44e-34967083c80a", notes = "The database generated Integer for directory/file id", required = true)
    private Integer id;

    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "(dir)(file.txt)", notes = "directory/file name", required = true)
    private String name;
    
    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "/", notes = "Path to directory/file", required = true)
    private String path;
    
    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "(drwxr-xr-x)(-rw-r--r--)", notes = "directory/file permisions", required = true)
    private String permisions;
    
    @ApiModelProperty(readOnly = true, value = "Integr", dataType = "Integer", example = "(2)(1)", notes = "The database generated Integer for directory/file id", required = true)
    private Integer link;
    
    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "root", notes = "User wich create directory/file", required = true)
    private String username;
    
    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "group", notes = "Group directory/file", required = true)
    private String groupname;
    
    @ApiModelProperty(readOnly = true, value = "Integr", dataType = "Integer", example = "4096", notes = "size of directory/file", required = true)
    private Integer size;
    
    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "Aug 7 10:51", notes = "The last modification date of the directory/file", required = true)
    private String time;
    
    @ApiModelProperty(readOnly = true, value = "Boolean", dataType = "Boolean", example = "true", notes = "If it is false some string is in text", required = true)
    private Boolean isDirectory;
    
    @ApiModelProperty(readOnly = true, value = "String", dataType = "String", example = "some string", notes = "If isDirectory is false some string is in here", required = true)
    private String text;
}
