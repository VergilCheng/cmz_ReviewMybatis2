package cn.uestc.test;

import org.junit.Test;

/**
 * 测试ThreadLocal
 */
public class TestThreadLocal {
    /**
     * 演示测试ThreadLocal:一个线程容器，可以给线程绑定一个Object内容，只要线程不变,可以随时
     * 取出
     */
    @Test
    public void test1() {
        ThreadLocal<String> local = new ThreadLocal<String>();
        //通过set给当前线程main线程绑定了一个String对象
        local.set("测试");
        String result = local.get();
        System.out.println(Thread.currentThread().getName()+":"+result);
    }
    /**
     * 演示测试ThreadLocal:改变线程,无法取出内容.
     */
    @Test
    public void test2() {
        ThreadLocal<String> local = new ThreadLocal<String>();
        //通过set给当前线程main线程绑定了一个String对象
        local.set("测试");
        new Thread(){
            public void run() {
                //在新启动的线程中尝试获取“测试”，获取不到，因为测试放在了main线程中
                String result = local.get();
                System.out.println(Thread.currentThread().getName()+"结果:"+result);
            };
        }.start();
    }
}
