package app.auToTest;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.Connection;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import sun.awt.windows.ThemeReader;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.*;

/**
 * Created by yumi on 2017/5/27.
 */
@Listeners({ScreenshotListener.class})
public class ifTest {
    //public AppiumDriver driver;
    static AndroidDriver driver;
    private boolean isInstall = false;

    public void startRecord() throws IOException {
        Runtime rt = Runtime.getRuntime();
        // this code for record the screen of your device
        rt.exec("cmd.exe /C adb shell screenrecord /sdcard/runCase.mp4");
    }

    public void ScreenShot(AndroidDriver driver){
        File screenShot = driver.getScreenshotAs(OutputType.FILE); //获取screenshot文件
        try {
            FileUtils.copyFile(screenShot, new File("./dir" + getDatetime() + ".jpg")); //文件copy到指定的文件夹
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public String getDatetime(){
        SimpleDateFormat date = new SimpleDateFormat("yyyymmdd hhmmss");
        return date.format(new Date());
    }

    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        //启动appium
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Huawei ale-cl00");
        //capabilities.setCapability("udid","b8bc1be0b242");  // ###########
        //capabilities.setCapability("udid","7c90834f");
        capabilities.setCapability("udid","M9N7N15A16005421");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("platformName","Android");
        //capabilities.setCapability("platformVersion","7.1.1");
        capabilities.setCapability("platformVersion","6.0");

        //配置测试apk
        capabilities.setCapability("appPackage", "com.douyaim.qsapp.ifme");
        capabilities.setCapability("appActivity", "com.douyaim.qsapp.activity.SplashActivity");
        capabilities.setCapability("newCommandTimeout",600);
        capabilities.setCapability("sessionOverride", true);    //每次启动时覆盖session，否则第二次后运行会报错不能新建session
        capabilities.setCapability("unicodeKeyboard", true);    //设置键盘
        capabilities.setCapability("resetKeyboard", false);     //设置默认键盘为appium的键盘
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //如果真机设备已经安装，则不需要重新安装
        if (isInstall) {
            File classpathRoot = new File(System.getProperty("user.dir")); // 获取当前程序所在目录
            File appDir = new File(classpathRoot, "apps");
            File app = new File(appDir, "iF-debug-201705201657.apk");
            capabilities.setCapability("app", app.getAbsolutePath());
        }
        System.out.println("in BeforeClass");

      //  startRecord();
    }

    @Test
    public void login() throws InterruptedException {
        try {
            Connection con = driver.getConnection();
            System.out.println(con);
            driver.findElementById("com.douyaim.qsapp.ifme:id/edit_view").sendKeys("13900000008");
            driver.findElementById("com.douyaim.qsapp.ifme:id/tvGetCheckCode").click();
            driver.findElementById("com.douyaim.qsapp.ifme:id/edit_view_check_code").sendKeys("0703");
            Thread.sleep(3000);
            driver.findElementById("com.douyaim.qsapp.ifme:id/action_sign_in").click();
            Thread.sleep(3000);
            String str = driver.findElementById("com.douyaim.qsapp.ifme:id/title_of_fc_list").getText().trim();
            //assertEquals("ME Life", str);
            assertEquals("1111测试环境!!!", str);
            Thread.sleep(8000);
            System.out.println("in login Test");
        }catch (Exception e){
            System.out.println("login error!!!!!!!!!!!!!!");
            e.printStackTrace();

            //重新开始
            //System.out.println("restart.............");
            //this.login();
        }
    }


    /*
    ** 发单聊消息，不带特效
     */
    //@Test(dependsOnMethods = { "login" })
    public void uploadMessagesToPerson() throws InterruptedException{
        Thread.sleep(2000);
        driver.findElementById("com.douyaim.qsapp.ifme:id/layout_to_menu").click();
        Thread.sleep(2000);
        driver.findElementById("com.douyaim.qsapp.ifme:id/friends").click();
        Thread.sleep(2000);

        List<WebElement> lis = driver.findElementsByClassName("android.widget.RelativeLayout");
        WebElement targetEle = lis.get(4);
        targetEle.click();
        Thread.sleep(2000);

        driver.findElementById("com.douyaim.qsapp.ifme:id/layout_send_video").click();
        Thread.sleep(2000);

        for(int i = 0; i < 10; i++) {
            driver.findElementById("com.douyaim.qsapp.ifme:id/btn_record_video_main").click();
            Thread.sleep(60000);
            driver.findElementById("com.douyaim.qsapp.ifme:id/btn_record_video_main").click();
            Thread.sleep(2000);
        }

        driver.findElementById("com.douyaim.qsapp.ifme:id/iv_change_view").click();

    }

    @Test
    public void atest3() throws InterruptedException{
        System.out.println("in atest3 Test");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        System.out.println("in AfterClass");
        driver.quit();
    }


    public static AppiumDriver getDriver(){
        return driver;
    }
}
