package com.lcy.type.generalconfig;

/**
 * 文章链接资源枚举
 *
 * @author hshande@linewell.com
 * @since 2019/01/09 17:57
 */
public enum ArticleLinkResourceEnum {

    NONE(0, "无"),
    ARTICLE(1, "文章");


    private int no; // 编号
    private String name; // 中文名称

    /**
     * 构造方法
     *
     * @param no   编号
     * @param name 中文名称
     */
    private ArticleLinkResourceEnum(int no, String name) {
        this.no = no;
        this.name = name;
    }

    /**
     * 获取类型
     *
     * @param typeStr 类型的字符串
     * @return
     */
    public static ArticleLinkResourceEnum getType(String typeStr) {
        for (ArticleLinkResourceEnum type : values()) {
            if (type.toString().equals(typeStr)) {
                return type;
            }
        }
        return null;
    }

    /**
     * 获取枚举名称
     *
     * @param no 序号
     * @return
     */
    public static String getTypeName(int no) {
        for (ArticleLinkResourceEnum type : values()) {
            if (no == type.getNo()) {
                return type.getName();
            }
        }
        return null;
    }

    /**
     * 获取编号
     *
     * @return
     */
    public int getNo() {
        return no;
    }

    /**
     * 获取中文名称
     *
     * @return
     */
    public String getName() {
        return name;
    }
}
