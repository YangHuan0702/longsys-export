package com.longsys.export.util.primaryKey;

import java.util.Random;
import java.util.UUID;

/**
 * 生成各类id获取编码
 * @author huan.yang
 * @date 2022-03-31
 */
public class IDUtil {

    private static final String[] KEYS = {"q", "a", "z", "o", "k", "m", "c", "f", "t"};

    /**
     * 生成唯一的主键
     * 格式: 时间+随机数
     */
    public static synchronized String getRandomId() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }


    /**
     * 获取唯一UUID
     *
     * @return
     */
    public static synchronized String getUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }

    /**
     * 由Twitter 的雪花算法生成id
     *
     * @return
     */
    public static long getUniqueId() {
        return SnowFlake.nextId();
    }

    /**
     * 生成各类编码
     *
     * @param prefix 编码前缀
     * @return
     */
    public static String buildCode(String prefix) {
        return prefix + random();
    }

    /**
     * 生成固定随机数
     *
     * @return
     */
    private static String random() {
        Integer number = new Random().nextInt(900000000) + 100000000;
        return String.valueOf(number);
    }

    /**
     * 生成随机数据中心id
     *
     * @return
     */
    public static Long getDataCenterId() {
        return (long) (Math.random() * 100 + 1);
    }

}
