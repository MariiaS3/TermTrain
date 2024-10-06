package com.term_train.ddd.vfs.infrastructure.port;

import com.term_train.ddd.vfs.domain.dto.RequestFile;
import com.term_train.ddd.vfs.domain.dto.VFSDto;
import com.term_train.ddd.vfs.domain.model.VFS;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface VFSUIPort {

    @GetMapping("/name/{name}")
    public ResponseEntity<List<VFSDto>> getFileByName(@PathVariable("name") String name);

    @GetMapping("/path-name/{fileRequest}")
    public ResponseEntity<VFSDto> getFileByNameAndPath(@RequestBody RequestFile fileRequest);

    @GetMapping("/path/{path}")
    public ResponseEntity<List<VFSDto>> getFileByPath(@PathVariable("path") String path);

    @PostMapping("/add-new-file")
    public ResponseEntity<VFSDto> createNewFile(@RequestBody VFS dirFile);

    @PutMapping("/update-file/{id}")
    public ResponseEntity<VFSDto> updateFile(@PathVariable String id, @RequestBody VFS file);

    @GetMapping("/cat/{fileRequest}")
    public ResponseEntity<String> getText(@RequestBody RequestFile fileRequest);

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
