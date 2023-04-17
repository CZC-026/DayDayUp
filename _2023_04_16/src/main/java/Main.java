public class Main {

    static int appleNum = 0;
    static int orangeNum = 0;
    static int capacity = 100;
    static final Object lock = new Object();



    public static void main(String[] args) {
        Runnable produceApple = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {
                        while (appleNum + orangeNum == capacity) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        appleNum++;
                        System.out.println(Thread.currentThread().getName()+"生产了第"+appleNum+"个苹果");
                        lock.notifyAll();
                        try {
                            Thread.sleep(3);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        Runnable produceOrange = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {
                        while (appleNum + orangeNum == capacity) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        orangeNum++;
                        System.out.println(Thread.currentThread().getName()+"生产了第"+orangeNum+"个橘子");
                        lock.notifyAll();
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        Runnable consumeOrange = () -> {
            synchronized (lock) {
                while (true) {
                    while (orangeNum == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    orangeNum--;
                    System.out.println(Thread.currentThread().getName()+"消费了第"+orangeNum+"个橘子");
                    lock.notifyAll();
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        Runnable consumeApple = new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (true) {
                        while (appleNum == 0) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        appleNum--;
                        System.out.println(Thread.currentThread().getName()+"消费了第"+appleNum+"个苹果");
                        lock.notifyAll();
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };
        new Thread(produceApple,"produce_apple1").start();
        new Thread(produceOrange,"produce_orange1").start();
        new Thread(consumeApple,"consume_apple1").start();
        new Thread(consumeOrange,"consume_orange1").start();

    }

}




