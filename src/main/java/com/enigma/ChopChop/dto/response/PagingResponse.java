package com.enigma.ChopChop.dto.response;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PagingResponse {
    private Long totalItems;
    private Integer totalPages;
    private Integer currentPage;
    private Integer pageSize;
}
