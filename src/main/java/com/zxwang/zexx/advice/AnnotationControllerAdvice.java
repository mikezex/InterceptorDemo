package com.zxwang.zexx.advice;

import com.zxwang.zexx.annotation.MyAnnotation;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

/**
 * 自定义 控制通知器，实现如果有注解 对数据的请求参数进行提前处理
 */
@ControllerAdvice
public class AnnotationControllerAdvice implements RequestBodyAdvice {

    /**
     * 首先调用以确定该拦截器是否适用。
     * 参数:
     * methodParameter—方法参数
     * targetType—目标类型，不一定与方法参数类型相同，例如对于HttpEntity<String>。
     * converterType -选择的转换器类型
     *
     * 返回:
     * 是否应该调用这个拦截器
     * @param methodParameter the method parameter
     * @param targetType the target type, not necessarily the same as the method
     * parameter type, e.g. for {@code HttpEntity<String>}.
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(MyAnnotation.class);
    }

    /**
     * 在读取和转换请求体之前第二次调用。
     * 参数:
     * inputMessage—请求参数—目标方法参数
     * targetType—目标类型，不一定与方法参数类型相同，例如对于HttpEntity<String>。
     * converterType -用于反序列化代码体的转换器
     * 返回:
     * 输入请求或新实例(绝不为空)
     * @param inputMessage the request
     * @param parameter the target method parameter
     * @param targetType the target type, not necessarily the same as the method
     * parameter type, e.g. for {@code HttpEntity<String>}.
     * @param converterType the converter used to deserialize the body
     * @return
     * @throws IOException
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        System.out.println("进入advice 拦截MyAnnotation注解的服务");
        if (inputMessage.getBody().available()<=0) {
            return inputMessage;
        }

        String requestStr =  new String(inputMessage.getBody().readAllBytes());
        System.out.println("requestStr:"+requestStr);
        inputMessage.getBody().read(inputMessage.getBody().readAllBytes());

        // 返回
        HttpInputMessage httpInputMessage = new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(requestStr.getBytes());
            }
            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };

        return httpInputMessage;
    }

    /**
     * 在请求体转换为Object后第三次(也是最后一次)调用。
     * 参数:
     * body -在第一个通知被称为inputMessage之前设置为转换对象-请求参数-目标方法参数targetType -目标类型，不一定与方法参数类型相同，例如对于HttpEntity<String>。converterType -用于反序列化代码体的转换器
     * 返回:
     * 相同的主体或新的实例
     * @param body set to the converter Object before the first advice is called
     * @param inputMessage the request
     * @param parameter the target method parameter
     * @param targetType the target type, not necessarily the same as the method
     * parameter type, e.g. for {@code HttpEntity<String>}.
     * @param converterType the converter used to deserialize the body
     * @return
     */
    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    /**
     * 如果主体为空，则第二次(也是最后一次)调用。
     * 参数:
     * body -通常在第一个通知被调用inputMessage之前设置为null -请求参数-方法参数targetType -目标类型，不一定与方法参数类型相同，例如对于HttpEntity<String>。converterType -选择的转换器类型
     * 返回:
     * 要使用的值，或者为空，如果需要该参数，则可能引发HttpMessageNotReadableException
     * @param body usually set to {@code null} before the first advice is called
     * @param inputMessage the request
     * @param parameter the method parameter
     * @param targetType the target type, not necessarily the same as the method
     * parameter type, e.g. for {@code HttpEntity<String>}.
     * @param converterType the selected converter type
     * @return
     */
    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
