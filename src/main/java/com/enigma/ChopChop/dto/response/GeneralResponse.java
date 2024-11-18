package com.enigma.ChopChop.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class GeneralResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private PagingResponse paging;
}
