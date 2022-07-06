package com.evaluation.fleet.AutoSuggest;

import com.evaluation.fleet.AutoSuggest.domain.CountySuggestion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@SpringBootApplication
public class AutoSuggestApplication {

	public static void main(String[] args) {
		SpringApplication.run(AutoSuggestApplication.class, args);
	}

}
