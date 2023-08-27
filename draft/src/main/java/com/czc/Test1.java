package com.czc;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Test1 {
    private static final int THREAD_POOL_SIZE = 10;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //待统计的目录
        String dirPath = "C:\\Java_projects";
        File dir = new File(dirPath);
        if(!dir.exists() || !dir.isDirectory()){
            System.out.println("Invalid Input dir path");
            return;
        }
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        List<Future> futureList = new ArrayList<>();
        long totalSize = 0L;
        //遍历获取目录下的所有文件，并提交到线程池中进行处理
        for(File file : listFiles(dir)){
            Future<Long> future = executorService.submit(() -> file.length());
            futureList.add(future);
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        for (Future<Long> future : futureList){
            totalSize = totalSize + future.get();
        }
        System.out.println(totalSize);
    }

    private static List<File> listFiles(File dir) {
        List<File> fileList = new ArrayList<>();
        for(File file : dir.listFiles()){
            if(file.isFile()){
                fileList.add(file);
            }else if(file.isDirectory()){
                fileList.addAll(listFiles(file));
            }
        }
        return fileList;
    }
}
