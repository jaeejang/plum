package org.plum.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class })
public class LoginTest {
	@Test
	public void Encypt() {
		PasswordEncoder encoder = new BCryptPasswordEncoder(9);
		for (int i = 0; i < 10; i++) {
			System.out.println(encoder.encode("minyan"));
		}
	}
}
