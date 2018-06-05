package com.yunpan.base.web.security.custm.holder;

import com.yunpan.base.web.security.custm.Authorization;
import com.yunpan.base.web.security.custm.ThreadHolder;

public class AuthHolder {
    private static ThreadHolder<Authorization> threadHoler = new ThreadHolder<Authorization>();

    public static void clearContext() {
        threadHoler.clearContext();
    }

    public static Authorization getAuthUser() {
        return threadHoler.getContext();
    }

    public static void setAuthUser(Authorization context) {
        threadHoler.setContext(context);
    }

}
