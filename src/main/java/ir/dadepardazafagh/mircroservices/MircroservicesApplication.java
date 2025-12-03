package ir.dadepardazafagh.mircroservices;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableRabbit
public class MircroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MircroservicesApplication.class, args);
	}

//	@Bean
//	@ConfigurationProperties("spring.datasource.hikari")
//	public HikariDataSource dataSource() {
//		return DataSourceBuilder.create().type(HikariDataSource.class).build();
//	}

}
