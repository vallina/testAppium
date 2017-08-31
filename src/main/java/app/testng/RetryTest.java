package app.testng;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 失败重试
 * @author likaige
 */
//@Listeners({ScreenshotListener.class, RetryListener.class})
public class RetryTest {


    @Test
    public void testRetry() {
        System.out.println("testRetry ...." + Thread.currentThread().getName());
        "".charAt(2);
        Assert.assertEquals(1, 2);
    }


}
