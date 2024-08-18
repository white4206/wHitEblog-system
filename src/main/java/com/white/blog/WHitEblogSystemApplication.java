package com.white.blog;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log4j2
@SpringBootApplication
public class WHitEblogSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WHitEblogSystemApplication.class, args);
        log.info(
                """
                        
                                           ┌─────────────────────────────────────────────────────────────┐
                                           │┌───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┬───┐│
                                           ││Esc│!1 │@2 │#3 │$4 │%5 │^6 │&7 │8  │(9 │)0 │_- │+= │|\\ │`~ ││
                                           │├───┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴───┤│
                                           ││ Tab │ Q │ W │ E │ R │ T │ Y │ U │ I │ O │ P │{[ │}] │ BS  ││
                                           │├─────┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴┬──┴─────┤│
                                           ││ Ctrl │ A │ S │ D │ F │ G │ H │ J │ K │ L │: ;│" '│ Enter  ││
                                           │├──────┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴─┬─┴────┬───┤│
                                           ││ Shift  │ Z │ X │ C │ V │ B │ N │ M │< ,│> .│? /│Shift │Fn ││
                                           │└─────┬──┴┬──┴──┬┴───┴───┴───┴───┴───┴──┬┴───┴┬──┴┬─────┴───┘│
                                           │      │Fn │ Alt │         Space         │ Alt │Win│   HHKB   │
                                           │      └───┴─────┴───────────────────────┴─────┴───┘          │
                                           └─────────────────────────────────────────────────────────────┘
                        
                        ================================================================================================
                                                            wHitE 博客前台服务端启动成功!
                        """
        );
    }

}
