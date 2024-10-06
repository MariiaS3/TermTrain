package com.term_train.ddd.vfs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestFile {
    private String commandName;
    private String file;
    private String options;
}
