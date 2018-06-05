package com.yunpan.base.web.security.custm;


public class ThreadHolder<T> {
    private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

    // ~ Methods
    // ========================================================================================================

    public void clearContext() {
        contextHolder.remove();
    }

    public T getContext() {
        return (T)contextHolder.get();
    }

    public void setContext(T context) {
        if(context!=null){
            contextHolder.set(context);
        }
    }
}
