package org.example.springboot2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Request {

    @Size(min = 1, max = 32)
    private String uid;

    @Size(min = 1, max = 32)
    private String operationUid;
    private String systemName;

    @NotBlank
    private String systemTime;

    private String source;

    @Size(min = 1, max = 100000)
    private int communicationId;

    private int templateId;
    private int productCode;
    private int smsCode;
}
