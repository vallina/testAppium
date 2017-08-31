package app.testng;

import app.auToTest.ScreenshotListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * 一个用例用多线程来执行多次
 * @author likaige
 */
@Listeners({ScreenshotListener.class, RetryListener.class})
public class MultiThreadTest {

    @Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
    public void testMethod() {
        Long id = Thread.currentThread().getId();
        System.out.println("Test method executing on thread with id: " + id);
        Assert.assertEquals(1, 2);
    }



    @Test
    public void testRetry() {
        Long id = Thread.currentThread().getId();
        System.out.println("testRetry executing on thread with id: " + id);
        Assert.assertEquals(1, 2);
    }


}
