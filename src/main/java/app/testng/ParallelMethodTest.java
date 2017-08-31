package app.testng;


import app.auToTest.ScreenshotListener;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ScreenshotListener.class, RetryListener.class})
public class ParallelMethodTest {
    @BeforeMethod
    public void beforeMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("beforeMethod. Thread id is: " + id);
    }

    @Test
    public void testMethodsOne() {
        long id = Thread.currentThread().getId();
        System.out.println("testMethodsOne. Thread id is: " + id);
    }

    @Test
    public void testMethodsTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("testMethodsTwo. Thread id is: " + id);
        Assert.assertEquals(1, 2);
    }

    @AfterMethod
    public void afterMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("afterMethod. Thread id is: " + id);

    }
}