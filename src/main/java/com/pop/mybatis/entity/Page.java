package com.pop.mybatis.entity;


import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by xugang on 16/7/12.
 */
public class Page<T> implements Serializable, Iterable {
    private List<T> content;
    private int total;
    private Pageable pageable;

    public Page() {

    }

    public Page(List<T> content, Pageable pageable, int total) {
        this.content = content;
        this.total = total;
        this.pageable = pageable;
    }

    public int getNumber() {
        return this.pageable == null ? 0 : this.pageable.getPageNumber();
    }

    public int getSize() {
        return this.pageable == null ? 0 : this.pageable.getPageSize();
    }

    public int getTotalPages() {
        return this.getSize() == 0 ? 1 : (int) Math.ceil((double) this.total / (double) this.getSize());
    }

    public boolean isFirstPage() {
        return !(this.getNumber() > 0);
    }

    public boolean hasNextPage() {
        return this.getNumber() + 1 < this.getTotalPages();
    }

    public boolean isLastPage() {
        return !this.hasNextPage();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }

    @Override
    public Iterator<T> iterator() {
        return this.content.iterator();
    }


}
