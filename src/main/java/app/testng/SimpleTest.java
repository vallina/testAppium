package app.testng;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Collection;

/**
 * Created by likg on 2017-07-08.
 */
public class SimpleTest {
    @Test
    public void test1() throws InterruptedException {
        Long id = Thread.currentThread().getId();
        System.out.println("test1.....=========" + id);
        for(int i=0; i<2; i++){
            System.out.println("i====="+i);
            Thread.sleep(1000);
        }

        Reporter.log("自定义的输出日志。。。。。123456");
        Reporter.log("自定义的输出日志。。。。。123456");
        Reporter.log("自定义的输出日志。。。。。123456");
        Reporter.log("自定义的输出日志。。。。。123456");
        Reporter.log("自定义的输出日志。。。。。123456");

        System.out.println("test1.......end");
        //Assert.assertEquals(1, 2);
    }




    @Test
    public void getApkFileAbsolutePath() {
        String path = "C:\\Users\\likg\\.jenkins\\workspace\\test1\\app\\build\\outputs\\apk";
        Collection<File> files = FileUtils.listFiles(new File(path), new String[]{"apk"}, false);
        for (File f : files) {
            System.out.println(f.getAbsolutePath());
        }
    }
}
