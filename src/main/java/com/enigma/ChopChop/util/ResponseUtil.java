package com.enigma.ChopChop.util;

import com.enigma.ChopChop.dto.response.GeneralResponse;
import com.enigma.ChopChop.dto.response.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtil {
    public static <T> ResponseEntity<GeneralResponse<T>> buildResponse(HttpStatus httpStatus, String msg, T data) {
        GeneralResponse<T> response = new GeneralResponse<>();
        response.setStatus(httpStatus.value());
        response.setMessage(msg);
        response.setData(data);
        return ResponseEntity.status(httpStatus).body(response);
    }

    public static <T> ResponseEntity<GeneralResponse<?>> buildResponsePage(HttpStatus httpStatus, String message, Page<T> page) {
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .currentPage(page.getPageable().getPageNumber() + 1)
                .pageSize(page.getSize())
                .build();
        GeneralResponse<List<T>> response = new GeneralResponse<>(
                httpStatus.value(),
                message,
                page.getContent(),
                pagingResponse
        );
        return ResponseEntity.status(httpStatus).body(response);
    }

}
