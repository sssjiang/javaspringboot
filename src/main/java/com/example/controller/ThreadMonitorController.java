package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 线程监控 Controller
 * 用于查看应用中的所有线程信息
 */
@RestController
public class ThreadMonitorController {

    @GetMapping("/threads")
    public Map<String, Object> getThreadInfo() {
        Map<String, Object> result = new HashMap<>();
        
        // 获取所有线程
        ThreadGroup rootGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup parentGroup;
        while ((parentGroup = rootGroup.getParent()) != null) {
            rootGroup = parentGroup;
        }
        
        Thread[] threads = new Thread[rootGroup.activeCount() * 2];
        int count = rootGroup.enumerate(threads, true);
        threads = Arrays.copyOf(threads, count);
        
        // 统计信息
        result.put("totalThreads", threads.length);
        
        // 线程列表
        List<Map<String, Object>> threadList = Arrays.stream(threads)
            .filter(Objects::nonNull)
            .map(thread -> {
                Map<String, Object> threadInfo = new HashMap<>();
                threadInfo.put("name", thread.getName());
                threadInfo.put("state", thread.getState().toString());
                threadInfo.put("priority", thread.getPriority());
                threadInfo.put("isDaemon", thread.isDaemon());
                threadInfo.put("isAlive", thread.isAlive());
                return threadInfo;
            })
            .sorted(Comparator.comparing(t -> (String) t.get("name")))
            .collect(Collectors.toList());
        
        result.put("threads", threadList);
        
        // 按状态分组统计
        Map<String, Long> stateCount = Arrays.stream(threads)
            .filter(Objects::nonNull)
            .collect(Collectors.groupingBy(
                t -> t.getState().toString(),
                Collectors.counting()
            ));
        result.put("stateCount", stateCount);
        
        // 按名称过滤（查找 Tomcat 和 Spring 相关线程）
        List<String> tomcatThreads = Arrays.stream(threads)
            .filter(Objects::nonNull)
            .map(Thread::getName)
            .filter(name -> name.toLowerCase().contains("tomcat") || 
                           name.toLowerCase().contains("http") ||
                           name.toLowerCase().contains("acceptor") ||
                           name.toLowerCase().contains("worker"))
            .collect(Collectors.toList());
        result.put("tomcatThreads", tomcatThreads);
        
        List<String> springThreads = Arrays.stream(threads)
            .filter(Objects::nonNull)
            .map(Thread::getName)
            .filter(name -> name.toLowerCase().contains("spring") ||
                           name.toLowerCase().contains("task") ||
                           name.toLowerCase().contains("scheduler"))
            .collect(Collectors.toList());
        result.put("springThreads", springThreads);
        
        return result;
    }
}

