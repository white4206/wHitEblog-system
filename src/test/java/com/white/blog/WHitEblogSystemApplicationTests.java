package com.white.blog;

import com.white.blog.utils.MD5Utils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WHitEblogSystemApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(
                MD5Utils.getMD5Password("12345678", "6d14e8d0-ec14-4ad9-a49a-44323a90e37c")
        );
    }

}
