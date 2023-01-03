package com.myCode.termTrain.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myCode.termTrain.dto.DirorfileDto;
import com.myCode.termTrain.model.Dirorfile;
import com.myCode.termTrain.service.DirorfileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "TermTrain Api", tags = "TermTrain Api",produces = "aplication/json")
@RestController
@RequestMapping("api/v1")
public class DirorfileController {

    @Autowired
    private final DirorfileService dirFileService;


    public DirorfileController(DirorfileService dirFileService) {
        this.dirFileService = dirFileService;
    }

    public ResponseEntity<?> addNewDirOrFile(@RequestBody Dirorfile dirFile){
        Dirorfile dirOrFile = dirFileService.addNewDirOrFile(dirFile);
        return new ResponseEntity<>(dirOrFile, HttpStatus.CREATED);
    }

    @ApiOperation(value = "get file/dir by name", response =  DirorfileDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 200 , message = "Succesfully retrieved file/dir by name"),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/name/{name}")
    public ResponseEntity<List<DirorfileDto>> getDirFileByName(@PathVariable("name") String name){
        List<DirorfileDto> dirFileDtos = dirFileService.getDirFileByName(name);
        return ResponseEntity.ok(dirFileDtos);
    }

    @ApiOperation(value = "get file/dir by name and path", response =  DirorfileDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 200 , message = "Succesfully retrieved file/dir by name and path"),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/path-and-name/**")
    public ResponseEntity<DirorfileDto> getDirFileByNameAndPath( HttpServletRequest link){
        System.out.println(link);
        int n = link.getRequestURI().split(link.getContextPath() + "/").length;
        String name = link.getRequestURI().split(link.getContextPath() + "/")[n-1];
        String temp = link.getRequestURI().split(link.getContextPath() + "/path-and-name/")[1];
        String path ="/";
        for(int i=1; i<temp.split("/").length-1; i++){
            path += temp.split("/")[i];
            if(i<temp.split("/").length-2){
                path += "/";
            }
        }
        DirorfileDto dirFileDto = dirFileService.getDirFileByNameAndPath(name, path);
        return ResponseEntity.ok(dirFileDto);
    }

    @ApiOperation(value = "get file/dir by path", response =  DirorfileDto[].class, produces = "aplication/json")  //about this endpoint
    @ApiResponses(value = {
        @ApiResponse(code = 200 , message = "Succesfully retrieved file/dir by path"),
        @ApiResponse(code = 403 , message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/path/**")
    public ResponseEntity<List<DirorfileDto>> getDirFileByPath(HttpServletRequest path){
        List<DirorfileDto> dirFileDtos = dirFileService.getDirFileByPath(path.getRequestURI()
        .split(path.getContextPath() + "/path/")[1]);
        return ResponseEntity.ok(dirFileDtos);
    }
}
