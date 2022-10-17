package com.lvtn.resource_server.lvtn_resource_server.apis.v1.dto.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PagingResponseDto {
	private int currentPage;
	private int currentQuantity;
	private int pageSize;
	private int totalPages;
	private int totalSize;

	public static PagingResponseDto getPagingResponse(int resultLen, int requestPage, int requestSize, int totalSize) {
		int totalPages = totalSize % requestSize == 0 ? (int) totalSize / requestSize : (int) totalSize / requestSize + 1;
		return PagingResponseDto.builder().currentQuantity(resultLen).pageSize(requestSize).currentPage(requestPage)
				.totalPages(totalPages).totalSize(totalSize).build();
	}
}
