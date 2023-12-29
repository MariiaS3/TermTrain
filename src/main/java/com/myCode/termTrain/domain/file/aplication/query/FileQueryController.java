package com.myCode.termTrain.domain.file.aplication.query;

import com.myCode.termTrain.domain.file.core.dto.FileDto;
import com.myCode.termTrain.domain.file.core.dto.RequestFile;
import com.myCode.termTrain.domain.file.core.service.query.FileQueryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class FileQueryController {
    @Autowired
    private final FileQueryService dirFileService;

    public FileQueryController(FileQueryService dirFileService) {
        this.dirFileService = dirFileService;
    }

    @ApiOperation(value = "get file/dir by name", response = FileDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfully retrieved file/dir by name"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/name/{name}")
    public ResponseEntity<List<FileDto>> getFileByName(@PathVariable("name") String name) {
        List<FileDto> fileDtos = dirFileService.getFileByName(name);
        return ResponseEntity.ok(fileDtos);
    }

    @ApiOperation(value = "get fileRequest/dir by name and path", response = FileDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfully retrieved fileRequest/dir by name and path"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/path-name/{fileRequest}")
    public ResponseEntity<FileDto> getFileByNameAndPath(@RequestBody RequestFile fileRequest) {
        FileDto fileDto = dirFileService.getFileByPathAndName(fileRequest);
        return ResponseEntity.ok(fileDto);
    }

    @ApiOperation(value = "get file/dir by path", response = FileDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfully retrieved file/dir by path"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @GetMapping("/path/{path}")
    public ResponseEntity<List<FileDto>> getFileByPath(@PathVariable("path") String path) {
        List<FileDto> fileDtos = dirFileService.getFileByPath(path);
        return ResponseEntity.ok(fileDtos);
    }
}
