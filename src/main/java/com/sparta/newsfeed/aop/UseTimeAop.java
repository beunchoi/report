package com.sparta.newsfeed.aop;

import com.sparta.newsfeed.entity.ApiUseTime;
import com.sparta.newsfeed.entity.User;
import com.sparta.newsfeed.repository.ApiUseTimeRepository;
import com.sparta.newsfeed.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j(topic = "UseTimeAop")
@Aspect
@Component
@RequiredArgsConstructor
public class UseTimeAop {

    private final ApiUseTimeRepository apiUseTimeRepository;

    @Pointcut("execution(* com.sparta.newsfeed.controller.CommentController.*(..))")
    private void comment() {}
    @Pointcut("execution(* com.sparta.newsfeed.controller.ProductController.*(..))")
    private void product() {}
    @Pointcut("execution(* com.sparta.newsfeed.controller.UserController.*(..))")
    private void user() {}
    @Pointcut("execution(* com.sparta.newsfeed.controller.WishController.*(..))")
    private void wish() {}

    @Around("comment() || product() || user() || wish()")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();

        try {
            Object output = joinPoint.proceed();
            return output;
        } finally {
            long endTime = System.currentTimeMillis();
            long runTime = endTime - startTime;

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth != null && auth.getPrincipal().getClass() == UserDetailsImpl.class) {
                UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
                User loginUser = userDetails.getUser();

                ApiUseTime apiUseTime = apiUseTimeRepository.findByUser(loginUser).orElse(null);
                if (apiUseTime == null) {
                    apiUseTime = new ApiUseTime(loginUser, runTime);
                } else {
                    apiUseTime.addUseTime(runTime);
                }

                log.info("Username: " + loginUser.getNickname() + ", Total Time: " + apiUseTime.getTotalTime());
                apiUseTimeRepository.save(apiUseTime);
            }
        }
    }
}
