package org.plum.test;

import org.plum.config.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DataConfig.class)
//@ComponentScan("service")
public class AppConfig {
	
}
