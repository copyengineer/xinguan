package com.xjb.newcrownweb;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class NewcrownWebApplicationTests {

	@Test
	void contextLoads() {
		Md5Hash hash = new Md5Hash("123456","helloShiro",1024);
		System.out.println(hash.toHex());
	}

	@Test
	void contextLoads2() {
		System.out.println(LocalDateTime.now().toString());
	}

}
