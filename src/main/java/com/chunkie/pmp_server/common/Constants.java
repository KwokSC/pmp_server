package com.chunkie.pmp_server.common;

/**
 * @Description:
 * @ClassName: MsgConstants
 * @Author: SichengGuo
 * @Date: 2021/12/14 17:22
 * @Version: 1.0
 */
public interface Constants {

    interface Msgs{
        String SUCCESS = "Successful request";
        String FAIL = "Fail request";
    }

    interface Code{
        Integer NORMAL = 200;
        Integer EXCEPTION = 500;
    }

}
