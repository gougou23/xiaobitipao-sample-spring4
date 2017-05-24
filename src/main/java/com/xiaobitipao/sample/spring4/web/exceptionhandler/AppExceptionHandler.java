package com.xiaobitipao.sample.spring4.web.exceptionhandler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobitipao.sample.spring4.exception.DataNotFoundException;

/**
 * <pre>
 * 标注@ControllerAdvice注解的类是控制器通知类，这个类会包含一个或多个如下类型的方法：
 * 1.标注@ExceptionHandler注解的方法，
 * 2.标注@InitBinder注解的方法，
 * 3.标注@ModelAttribute注解的方法。
 * 标注@ControllerAdvice注解的类中，以上所述的这些方法会运用到整个应用程序所有控制器中带有@RequestMapping注解的方法上。
 * 
 * 注解@ControllerAdvice最为实用的一个场景就是将所有的@ExceptionHandler方法收集到一个类中，
 * 这样所有的控制器异常就能在一个地方进行一致的处理。
 * </pre>
 */
@ControllerAdvice
public class AppExceptionHandler {

    /**
     * 文件上传出现异常的处理。
     * 
     * @param exception
     *            异常
     * @return view
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ModelAndView handlerMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {

        return new ModelAndView("error/upload").addObject("msg", "文件太大！");
    }

    /**
     * 文件上传出现异常的处理。
     * 
     * @param exception
     *            异常
     * @return view
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ModelAndView handlerDataNotFoundException(DataNotFoundException exception) {
        return new ModelAndView("error/hasnotdata").addObject("msg", exception.getMessage());
    }
}
