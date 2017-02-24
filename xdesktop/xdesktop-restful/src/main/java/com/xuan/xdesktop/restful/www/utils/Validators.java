package com.xuan.xdesktop.restful.www.utils;

import java.util.Collection;

/**
 * 对字符串按照常用规则进行验证的工具类.
 *
 * @author xuan
 * @version $Revision$, $Date$
 */
public abstract class Validators {

    /**
     * 当数组为<code>null</code>, 或者长度为0, 或者长度为1且元素的值为<code>null</code>时返回 <code>true</code>.
     *
     * @param args
     * @return true/false
     */
    public static boolean isEmpty(Object[] args) {
        return args == null || args.length == 0 || (args.length == 1 && args[0] == null);
    }

    /**
     * 字符串是否为Empty，null和空格都算是Empty
     *
     * @param str 字符串
     * @return true/false
     */
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    /**
     * 判断集合是否为空。
     *
     * @param <T>        集合泛型
     * @param collection 集合对象
     * @return 当集合对象为 <code>null</code> 或者长度为零时返回 <code>true</code>，否则返回 <code>false</code>。
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

}
