package kr.co.orangenode.dto.article;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class PageResponseDTO {
    private List<ArticleDTO> dtoList;
    private int pg;
    private int size;
    private int total;
    private String cateName;
    private String sort;
    private String how;

    private int start, end;
    private boolean prev, next;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<ArticleDTO> dtoList, int total){

        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.cateName = pageRequestDTO.getCateName();
        this.sort = pageRequestDTO.getSort();
        this.how = pageRequestDTO.getHow();
        this.dtoList = dtoList;
        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;

        int last = (int) (Math.ceil(total / (double)size));
        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }

}
