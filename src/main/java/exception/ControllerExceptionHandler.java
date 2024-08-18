package exception;

import com.white.blog.common.R;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.lang.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author white_
 * @version 1.0
 * @project HuaJiao-system
 * @description 自定义异常处理类
 * @date 2024/4/19 22:01:10
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<String> handle401(ShiroException e) {
        return R.error("授权认证失败");
    }

    // 捕捉未授权的异常
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public R<String> handle401(UnauthorizedException e) {
        return R.error("用户角色授权失败");
    }

}
