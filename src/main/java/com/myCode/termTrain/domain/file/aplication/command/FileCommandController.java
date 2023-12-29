package com.myCode.termTrain.domain.file.aplication.command;

import com.myCode.termTrain.domain.file.core.dto.FileDto;
import com.myCode.termTrain.domain.file.core.model.File;
import com.myCode.termTrain.domain.file.core.service.command.FileCommandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class FileCommandController {
    @Autowired
    private final FileCommandService fileCommandService;

    public FileCommandController(FileCommandService fileCommandService) {
        this.fileCommandService = fileCommandService;
    }

    @ApiOperation(value = "add new file/dir", response = FileDto[].class, produces = "aplication/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Succesfully added file/dir"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "not found resource")

    })
    @PostMapping("/add-new-file")
    public ResponseEntity<FileDto> createNewFile(@RequestBody File dirFile) {
        FileDto file = fileCommandService.createNewFile(dirFile);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    @PutMapping("/update-file/{id}")
    public ResponseEntity<FileDto> updateFile(@PathVariable String id, @RequestBody File file) {
        return ResponseEntity.ok(fileCommandService.updateFile(id, file));
    }
}
