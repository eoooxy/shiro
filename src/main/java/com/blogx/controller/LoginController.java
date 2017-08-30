package com.blogx.controller;

import com.blogx.constant.ResConstant;
import com.blogx.utils.ResUtils;
import com.blogx.utils.ShiroUtils;
import com.blogx.utils.VerifyCodeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * author： xueyuan
 * date  ： 2017-08-29 下午1:51
 */
@RestController
public class LoginController {

    @PostMapping("login")
    public String postLogin(String username, String password) {

        if (null == username || "".equals(username) || null == password || "".equals(password)) {
            return ResUtils.err("账号或密码不能为空");
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResUtils.err(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResUtils.err(e.getMessage());
        } catch (LockedAccountException e) {
            return ResUtils.err(e.getMessage());
        } catch (AuthenticationException e) {
            return ResUtils.err("账户验证失败");
        }
        return ResUtils.ok();
    }

    /**
     * 得到验证码流
     *
     * @param request
     * @param response
     * @param session
     * @throws IOException
     */
    @GetMapping("verify")
    public void imageCode(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        //生成随机字串
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4);
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase());
        //生成图片
        int w = 100, h = 36;
        VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
    }

    /**
     * 验证输入的验证码
     *
     * @param session
     * @param verifycode
     * @return
     */
    @PostMapping("verifycode")
    public String verityCode(HttpSession session, String verifycode) {

        if (StringUtils.isBlank(verifycode) && verifycode == null) {
            return ResUtils.err();
        }
        String vercode = (String) session.getAttribute("verCode");
        if (StringUtils.isBlank(vercode)) {
            return ResUtils.err();
        }
        if (vercode.equalsIgnoreCase(verifycode)) {
            return ResUtils.ok();
        } else {
            return ResUtils.err();
        }
    }

    /**
     * 当前角色没有权限的 返回值
     *
     * @return
     */
    @GetMapping("unauthor")
    public String unAuthor() {
        return ResUtils.other(ResConstant.NO_AUTHOR_CODE, ResConstant.NO_AUTHOR_MSG);
    }
}
