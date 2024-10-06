package com.term_train.ddd.vfs.application;

import com.term_train.ddd.vfs.domain.VFSFacade;
import com.term_train.ddd.vfs.domain.dto.VFSDto;
import com.term_train.ddd.vfs.domain.dto.RequestFile;
import com.term_train.ddd.vfs.domain.model.VFS;
import com.term_train.ddd.vfs.infrastructure.port.VFSUIPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "TermTrain Api", tags = "TermTrain Api", produces = "aplication/json")
@RestController
@RequestMapping("/api/v1")
public class VFSCommandController implements VFSUIPort {
    private final VFSFacade vfsFacade;

    public VFSCommandController(VFSFacade dirFileService) {
        this.vfsFacade = dirFileService;
    }

    @ApiOperation(value = "get file/dir by name", response = VFSDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfully retrieved file/dir by name"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @Override
    public ResponseEntity<List<VFSDto>> getFileByName(@PathVariable("name") String name) {
        List<VFSDto> fileDtos = vfsFacade.findFileByName(name);
        return ResponseEntity.ok(fileDtos);
    }

    @ApiOperation(value = "get fileRequest/dir by name and path", response = VFSDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfully retrieved fileRequest/dir by name and path"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @Override
    public ResponseEntity<VFSDto> getFileByNameAndPath(@RequestBody RequestFile fileRequest) {
        VFSDto fileDto = vfsFacade.findFileByPathAndName(fileRequest);
        return ResponseEntity.ok(fileDto);
    }

    @ApiOperation(value = "get file/dir by path", response = VFSDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfully retrieved file/dir by path"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @Override
    public ResponseEntity<List<VFSDto>> getFileByPath(@PathVariable("path") String path) {
        List<VFSDto> fileDtos = vfsFacade.findFileByPath(path);
        return ResponseEntity.ok(fileDtos);
    }

    @ApiOperation(value = "add new file/dir", response = VFSDto[].class, produces = "aplication/json")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Succesfully added file/dir"), @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"), @ApiResponse(code = 404, message = "not found resource")

    })
    @Override
    public ResponseEntity<VFSDto> createNewFile(@RequestBody VFS dirFile) {
        VFSDto file = vfsFacade.createNewFile(dirFile);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<VFSDto> updateFile(@PathVariable String id, @RequestBody VFS file) {
        return ResponseEntity.ok(vfsFacade.updateFile(id, file));
    }

    @ApiOperation(value = "get text from file (cat)", response = VFSDto[].class, produces = "aplication/json")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved file/dir by name and path"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "not found resource")

    })
    @Override
    public ResponseEntity<String> getText(@RequestBody RequestFile fileRequest) {
        String cat = vfsFacade.getFileTextByPathAndName(fileRequest);
        return ResponseEntity.ok(cat);
    }
//    @ApiOperation(value = "get text from file (cat)", response = VFSDto[].class, produces = "aplication/json")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Succesfully retrieved file/dir by name and path"),
//            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
//            @ApiResponse(code = 404, message = "not found resource")
//
//    })
//    @GetMapping("/cat/{fileRequest}")
//    public ResponseEntity<String> getText(@RequestBody RequestFile fileRequest) {
//        VFSDto fileDto = dirFileService.getFileByPathAndName(fileRequest);
//        return ResponseEntity.ok(fileDto.getText());
//    }
}
