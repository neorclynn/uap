package neo.uap.aspect;

import neo.uap.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.Method;

@Aspect
@Component
public class AuthAspect {
    @Pointcut("execution(* neo.uap.controller..*(..)) " +
            " && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void serviceCall() {
    }

    @Around("serviceCall()")
    public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!hasAuthority(joinPoint)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return joinPoint.proceed();
    }

    private boolean hasAuthority(JoinPoint joinPoint) {
        return UserUtil.hasAuthority(getHttpMethod(joinPoint), getPath(joinPoint));
    }

    private String getPath(JoinPoint joinPoint) {
        return getPathOnClass(joinPoint) + getPathOnMethod(joinPoint);
    }

    private String getPathOnClass(JoinPoint joinPoint) {
        RequestMapping mapping = getClassDefinition(joinPoint).getAnnotation(RequestMapping.class);
        if (mapping == null) {
            return "";
        }

        String[] paths = mapping.value();
        return (paths == null || paths.length == 0) ? "" : paths[0];
    }

    private String getPathOnMethod(JoinPoint joinPoint) {
        String[] paths = getMethodDefinition(joinPoint).getAnnotation(RequestMapping.class).value();
        return (paths == null || paths.length == 0) ? "" : paths[0];
    }

    private Method getMethodDefinition(JoinPoint joinPoint) {
        return ((MethodSignature) joinPoint.getSignature()).getMethod();
    }

    private Class<?> getClassDefinition(JoinPoint joinPoint) {
        return getMethodDefinition(joinPoint).getDeclaringClass();
    }

    private String getHttpMethod(JoinPoint joinPoint) {
        String method;

        method = getHttpMethodOnMethod(joinPoint);
        if (StringUtils.isNotEmpty(method)) {
            return method;
        }

        method = getHttpMethodOnClass(joinPoint);
        if (StringUtils.isNotEmpty(method)) {
            return method;
        }

        return RequestMethod.GET.name();
    }

    private String getHttpMethodOnClass(JoinPoint joinPoint) {
        RequestMethod[] methods = getClassDefinition(joinPoint).getAnnotation(RequestMapping.class).method();
        return (methods == null || methods.length == 0) ? null : methods[0].name();
    }

    private String getHttpMethodOnMethod(JoinPoint joinPoint) {
        RequestMethod[] methods = getMethodDefinition(joinPoint).getAnnotation(RequestMapping.class).method();
        return (methods == null || methods.length == 0) ? null : methods[0].name();
    }
}
