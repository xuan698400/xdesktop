package com.xuan.xdesktop.restful.www.controller;

import com.xuan.xdesktop.restful.www.enums.Code;
import com.xuan.xdesktop.restful.www.exception.XpException;
import com.xuan.xdesktop.restful.www.utils.Validators;

/**
 * Created by xuan on 17/2/22.
 */
public class BaseController {
    /**
     * 验证-全部为空
     *
     * @param strs
     */
    protected void validateParam(String... strs) {
        for (String str : strs) {
            if (Validators.isEmpty(str)) {
                throw new XpException(Code.ERROR_PARAM);
            }
        }
    }
}
