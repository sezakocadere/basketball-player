package com.basketball.basketball.service.aspect;

import com.basketball.basketball.enums.Operation;
import com.basketball.basketball.service.history.HistoryService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class Logging {
    private final HistoryService historyService;

    @Pointcut("execution(* com.basketball.basketball.service.team.TeamServiceImpl.createTeam(..)) || execution(* com.basketball.basketball.service.player.PlayerServiceImpl.createPlayer(..))")
    private void saveCreateData() {
    }

    @Pointcut("execution(* com.basketball.basketball.service.player.PlayerServiceImpl.removePlayer(..))")
    private void saveDeleteData() {
    }

    @Pointcut("execution(* com.basketball.basketball.service.player.PlayerServiceImpl.preparePlayer(..))")
    private void saveUpdateData() {
    }

    @AfterReturning(pointcut = "saveCreateData()", returning = "result")
    public void create(JoinPoint joinPoint, Object result) {
        historyService.save(Operation.CREATE, result.toString());
    }

    @AfterReturning(pointcut = "saveUpdateData()", returning = "result")
    public void update(JoinPoint joinPoint, Object result) {
        historyService.save(Operation.UPDATE, result.toString());
    }

    @AfterReturning(pointcut = "saveDeleteData()", returning = "result")
    public void delete(JoinPoint joinPoint, Object result) {
        historyService.save(Operation.DELETE, result.toString());
    }
}
