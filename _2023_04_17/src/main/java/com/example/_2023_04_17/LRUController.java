package com.example._2023_04_17;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class LRUController {
    @Resource
    private LRUService lruService;

    @GetMapping("/get/{key}")
    public Result getNodeByKey(@PathVariable("key") int key) {
        return lruService.GetNodeByKey(key);
    }

    @GetMapping("/get/size")
    public Result getSize() {
        return lruService.getSize();
    }
    @GetMapping("/get/cache")
    public Result getCache(){
        return lruService.getCache();
    }

    @PostMapping("/post")
    public Result postNode(@RequestParam int key, @RequestParam int value) {
        return lruService.PostNode(key, value);
    }

    @DeleteMapping("/delete")
    public Result deleteNodeByKey(@RequestParam int key) {
        return lruService.deleteNodeByKey(key);
    }

}
