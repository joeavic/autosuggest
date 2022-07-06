package com.evaluation.fleet.AutoSuggest;

import com.evaluation.fleet.AutoSuggest.config.RedisUtil;
import com.evaluation.fleet.AutoSuggest.suggestion.SuggestionController;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
class AutoSuggestApplicationTests {

	@Autowired
	private SuggestionController suggestionController;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RedisUtil redisUtil;

	@Test
	void contextLoads() {
	}

	@Test
	public void testAPIResultForQueryCA() throws Exception{
		this.mockMvc.perform(get("/suggest")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("{\"fips\":\"13043\",\"state\":\"GA\",\"name\":\"Candler\"}")));
	}

}
