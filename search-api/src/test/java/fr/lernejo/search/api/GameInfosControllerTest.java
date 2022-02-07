package fr.lernejo.search.api;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class GameInfosControllerTest {
    @Test
    void getGameInfoValidURI(@Autowired MockMvc mockMvc)  {
        assertAll(() -> mockMvc
            .perform(MockMvcRequestBuilders.get("/api/games?query=developer:Epic Games"))
            .andExpect(MockMvcResultMatchers.status().isOk()));
    }
    @Test
    void getGameInfoInvalidURI(@Autowired MockMvc mockMvc) throws Exception {
            mockMvc
                .perform(MockMvcRequestBuilders.get("/api/gamesToTO"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
