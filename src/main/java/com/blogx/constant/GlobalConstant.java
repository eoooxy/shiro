package com.blogx.constant;

/**
 * author： xueyuan
 * date  ： 2017-09-08 上午10:32
 */
public class GlobalConstant {


    // 有效标志
    public static final Integer ENABLE = 1;
    public static final Integer DISABLE = 0;
    // MD5 加密的盐
    public static final String SALT = "shiro";
    //用户默认密码（如果没有填写密码的话）
    public static final String DEFAULT_PWD = "123456";
    //默认页面大小与页码
    public static final Integer PAGE_SIZE = 10;
    public static final Integer PAGE_NUMBER = 1;


    public enum menuType {

        List(1, "目录"), Menu(2, "菜单"), Btn(3, "按钮");
        private Integer code;
        private String des;

        menuType(Integer code, String des) {
            this.code = code;
            this.des = des;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }
    }


}
