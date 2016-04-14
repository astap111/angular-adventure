package org.itechart.other;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableSortedById implements Pageable {
    private int page;
    private int pageSize;

    public PageableSortedById(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    @Override
    public int getPageNumber() {
        return page;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return page * pageSize;
    }

    @Override
    public Sort getSort() {
        return new Sort("id");
    }
}
