package com.wmq.sys.utils.mybatis.paginatc;


import com.wmq.sys.utils.mybatis.paginatc.domain.PageBounds;

/**
 * Created by Administrator on 2016/5/24 0024.
 */
public class PageContext {
    private final static ThreadLocal<PageBounds> threadPage = new ThreadLocal<PageBounds>();

    public static PageBounds getPage() {
        if (threadPage.get() == null) {
            setPage(new PageBounds(1, 10));
        }
        return threadPage.get();
    }

    public static void setPage(PageBounds page) {
        threadPage.set(page);
    }

    public static int getTotalCount(){
        if(getPage()!=null && getPage().getPaginator()!=null) {
            return getPage().getPaginator().getTotalCount();
        }else {
            return 0;
        }
    }
}
