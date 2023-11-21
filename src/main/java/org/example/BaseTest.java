package org.example;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.*;
import java.time.Duration;
import java.util.*;

public class BaseTest {
    public static WebDriver driver;
    public static Properties prop;

    public BaseTest() {
        try {
            prop = new Properties();
            FileInputStream fi = new FileInputStream("D:\\New folder\\VisitDesk\\src\\main\\resources\\config.properties");
            prop.load(fi);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void initialization () throws InterruptedException {
            String browserName = prop.getProperty("browser");
            if (browserName.equals("chrome")) {
                System.setProperty("webdriver.chrome.driver", "D:\\New folder\\chromedriver-win64\\chromedriver.exe");
                ChromeOptions options = new ChromeOptions();
                options.setAcceptInsecureCerts(true);
                driver = new ChromeDriver(options);
            } else if (browserName.equals("edge")) {
                System.setProperty("webdriver.edge.driver", "C:\\Users\\Toufi\\Downloads\\New folder\\msedgedriver.exe");
                EdgeOptions options = new EdgeOptions();
                driver = new EdgeDriver(options);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(prop.getProperty("url"));
        }

    @BeforeMethod
    public void setUp() throws InterruptedException, IOException {
        initialization();
    }
    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}
//    SessionManager sessionManager = new SessionManager(driver);
//    JSONObject payload2=new JSONObject();
////        payload2.put("code","irshad");
//        payload2.put("token","03AFcWeA4GApon0mX2EtjF_3_szUZXcQK8XEgahkXW_c7tol9eTlgjkYcI0GRj1dMWJ4MQzxD3weSwT6wUYSZFrTZz4v5vzxU3OM-rznt-CQM0fJ34gKQMHcZfpxLimmMv3zI7LjMO3fmE7OvN-HC8zRReOtuC_jYNQJnVvPiPdUuF_3vEI0HYYokfuGG_pv6j_pUD3LIZHxXBmq5XqxkC_iJn3CHlqMMwz6z4spW87KmWXJsbvfMG9kIceg9XzWIAW8-ZmqmOHRZW-WZNIYnTMAnOrcqb7QucNoPlQQuq4LWXKTT1CvZFISQdL1ek4ZDnn76_PE-NfmJ_-6iCV_YDPgke3QT0f5njSuKXisXQulR5GU-2xIUZ00eeItTT-ZUwy9AaNEM2ThhJUIKRJ3E2oSWgl84fjmYVcl13SVGjeDZuXd2wsOiai11gT4e1NfMfnIzeDFg6dVJoWsLdayOpG2BJfvcvsfnQzzYJBX20oTZWjXDBSvnao-mxzDvyk0CTIcjKiZNmfKUo6ffnNTqlfOHqIhR1pjYk0Qnrv6qB4rq_v-l7Tp_jjo-x4dPgju-S5NbyiqMzgeXaCeKL_MWt6fID3Fe_JOajCXK6bIChOcxy-kov6hEOSB-lfUC0RGwsTE0UsYtgxBsUT9tLBVYUoANWJumjl7C5qXaAcTgV-Oz-5UebM3DSfSgzVV55yAUOCqLAuYoiGq9txN8tCYOF6eP_W3YvmDosqgUNK0Gvmpqj-oR9YCRd3ui5VAcMLpsIz1QGEiZY21bjJcw5-sXIQbUZkhusoUX7AIErz0st7H8KdnZGtOVFC8P2mVeI0Mmmd24MGPODZ52Hto6RQVngpzQfr02IK6lituiyPRzmwvKlzUaHaNXjKXpsZ8AstJ3Mhyn-KaXEGjtwEkMXgAmmTlWKsdSiGIFkZydjCmFko5pJfm8XCYQ8fwExhhacDojt-08FfagEhdZMDeLLaPFQ02Ie5KeEscoHvDP4rND-hjrhHRjAPfGEdsSQFEL7a9nqKaVRyvz8ilP75rrNbE1Ede3Mm2XKobbXnXHtAa2DdxOfH3rpWjecMRWKahiQHVQfpI4fDbajKknwM4FZewgujoZd8pgL-l3cYxlqlsQIVbcNvJ6B1y1ZEFo57MvJCMwj11DPz3hd_PEKuJiKjQ4b-ICDIaiAsVH2VwBdUqvYwSv8HF9fE_y6UhrSG-PKLNf0F6MAoARvNU2tum5SEbPVs0iJgKWQac5zSm0DolwF8hIhaLPWZEqQj71WZbiBRK6LPQbRQT50yCuWAPVyZsd8IXfXuUMqsRVr-QKp_h4rDwOQR_9O87CuKRsWR3351empWFaYIdvi_UQZ3BUKmHPd5KAiABidNm_Dg5U59xIHT7XO9NBLaaXQIHfko5cVKX6-4kQu8TYxD2sua2S3JZsix9hYPY3shl02ye2Y868w5FP6uYZz6tFpYEXsvsuvXJyD2kr-_H-NwvC6bQyUJVAR8Z1WzjBRneRwGqo9IRdi1cx3wQ84n45awQU-hWXe75MPRmPVZ_TTeDbHaGI3Vkca24fgzk1CCtkYyA");
//        String Uri2="https://app.visitdesk.io/open/validatecaptcha";
//        Map<String, String> cookies2 =  new ApiUtils().postCallGetCookies(payload2,Uri2);
//        Set<Cookie> allCookies2 = driver.manage().getCookies();
//        System.out.println("All Cookies: " + allCookies2);
//        sessionManager.byPassLoginUsingCookies(cookies2,"app.visitdesk.io");
//
//
//        JSONObject payload3 = new JSONObject();
//        payload3.put("businessCode", "irshad123");
//        payload3.put("username", "irshad123");
//        payload3.put("password", "1245irS#");
//        payload3.put("captchaToken", "03AFcWeA7lJVE2UjOpIjbYdHStwhaZ9BUxQFmT_XPnSIe3XDrP2qy5ldIr-mCrWepG8VinpOSv_4fwozHiUUkBePfvVCWisx-aX0A3g_7b5zPcLoXoBmcmsZvCQtD4ZqtEDJNFE8PhrHgsPPKpnT512xm4_9yaES__WBfLnf82vQg65qo7dy7cqkWj57wo-YI4lbIuIORnpt1saWTni63EP8-Zip5Si3ZNDP-yh_-9kXubjebdMUW__bbI5ImGJ4obn9GkY8ir7iDIpaNHFIfHt6kxAubt5eN3GWAhKWMEHCHP0F7lX1sQp3GimNThEpjaGgIkSS43L7jvwt6dILPrsbOfe1Awb51eLLU_7AQGg4cY2nXInwtAqkoKdW2BPPJeyTvMQMm8ktpojcUwvgtDsnSYnJL52fRLcyk_Zz2b_ccsv2qG8MyoK5B22L4jijpLjHiWAcVenUa0kodtBQsAS_bznvsTfayjjUU9gNBeAvCJBGQHUOR-vP5bogRRauQML45SQHcF1avBiuwi2Z0_7aq4r33cJdsl82xz4y4Q7IsuwB20s6AXx2g6_dMKwjC883e5MslhIUTBpMSJGtgKXb1eQYfqLBa3A8vNYgzVln57s8ysvuHaA89XX9EeLdDw__K7WLT9s2zXe56YTFo2IWUhbgfgo5IFpN9PXN68A7aj-b4q6ZrXIDtSRFumgDiEFDeHqDS7gxOmkQy9NcbD-2hAa8ZS4yFPQE2TLZd7jJNI1eK5isgW-_6CfIYnZZbD2NOW3PleKq2UkDKNB24qHlSt2mRt2Mz6Thu7spQsc3WWshS3TGNBAS_Qw5Nw9U0Nc7ihr28h0sqhEg94bwJa02RyprJoAQvXR_YI0EiGfJAl9RK8S2KID2RnCnKtwooN6K42BRA3FAki47CyIkIZFbnqSBDV4GZCefwgkPHf_MbHKiUHjMepK0ydVstnIaPfu0mg67AiNztOrOM29g3LmWCx_BbTWQueZzdeXcPiUw5DDDgYq5JTjyi1Yu3iQrqYhHIKxG5dAhi_3vFKUiEytnVEVzyyQkwrKYiVLdxHAsWa1PsqZP1c0FHSF_o-ZxbZ6rcqqbvCapi4AB0DgmpBUEd_of5DpMKTw_kGWo4tJQl0LQgNk-_MAFo81vnMw_vdpc7dy98aeUpDx9HNmfbiIl8js4gg7haeWOQLMha27xl85BCtRw3Oip-R5hfrkNlPwaDP2PPtuUJj3UDwoiCkJjtHKPh0nO8v4mj4UqgDiSRjIPEiAnaAckSTbGkSY4tOMY1kSAvBU4KW9dtkJsu3YgIrlaJ9zindsWqbt-bdC2mdz1I8cQ-ileTmtVvX8HB5PlYcXJSkTxq7xGho7EE2njZ0x3mq6ALGrtU7vg7IR3aJyM0p5z6Bz2DOS2MBJgq9pbD3veMzln51anzW9Ba75PcIIVY641gowxNDBeZENIBzoYvkNbASapLKO2rlgp0AUeUY19jYgGb9We1i6VO0IU3g7sCYWXNnWdUyTYJ4UDj8mcHDZ-NN8lro8bNIocxRHOZOSgRN0asl"); // Captcha token is provided here
//        String uri3 = "https://app.visitdesk.io/open/login";
//        Map<String, String> cookies3 = new ApiUtils().postCallGetCookies(payload3, uri3);
//        Set<Cookie> allCookies3 = driver.manage().getCookies();
//        System.out.println("All Cookies: " + allCookies3);
//        sessionManager.byPassLoginUsingCookies(cookies3, "app.visitdesk.io");
//  }
//          } catch (FileNotFoundException e) {
//          throw new RuntimeException(e);
//          } catch (IOException e) {
//          throw new RuntimeException(e);
//          }
//          }