package app.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;


/**
 * Created by likg on 2017-07-04.
 */
public class TestngRetry implements IRetryAnalyzer {
    private static Logger logger = Logger.getLogger(TestngRetry.class);
    private int retryCount = 1;
    private static int maxRetryCount = 3;

    //private static ConfigReader config;
    static {

        //外围文件配置最大运行次数
//        config = new ConfigReader(TestngListener.CONFIG);
//        maxRetryCount = config.getMaxRunCount();
        logger.info("maxRunCount=" + (maxRetryCount));
    }

    @Override
    public boolean retry(ITestResult result) {
        System.out.println("TestngRetry retry......");
        if (retryCount <= maxRetryCount) {
            String message = "running retry for  '" + result.getName() + "' on class " +
                    this.getClass().getName() + " Retrying " + retryCount + " times";
            System.out.println(message);
            Reporter.setCurrentTestResult(result);
            Reporter.log("RunCount=" + (retryCount + 1));
            retryCount++;
            return true;
        }
        return false;
    }

}
