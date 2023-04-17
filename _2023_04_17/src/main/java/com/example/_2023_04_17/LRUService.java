package com.example._2023_04_17;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class LRUService {
    @Resource
    private LRUCache cache;
    @PostConstruct
    private void init(){
        cache = new LRUCache();
    }


    public Result GetNodeByKey(int key) {
        int res = cache.get(key);
        if(res == -1){
            return Result.fail("不存在对应key的缓存");
        }
        return Result.ok(res);
    }

    public Result PostNode(int key, int value) {
        try {
            cache.put(key,value);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("上传节点异常");
        }
        return Result.ok();
    }

    public Result deleteNodeByKey(int key) {
        try {
            cache.delete(key);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("删除节点异常");
        }
        return Result.ok();
    }

    public Result getSize() {
        return Result.ok(cache.getSize());
    }

    public Result getCache() {
       return Result.ok(cache.getAllNodes());
    }
}
