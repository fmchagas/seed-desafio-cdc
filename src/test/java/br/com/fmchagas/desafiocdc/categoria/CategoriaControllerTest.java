package br.com.fmchagas.desafiocdc.categoria;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoriaControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Test
    void deveRetornar201_QuandoCadastrarCategoria() throws JsonProcessingException{
        try {
            String payload = new ObjectMapper().writeValueAsString(
                Map.of("nome", "Categoria1")
            );
            System.out.println(payload);
            mvc.perform(post("/api/v1/categorias")
            .contentType("application/json")
            .content(payload))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andDo(MockMvcResultHandlers.print());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveRetornar400_QuandoDadosForemInvalidos() throws JsonProcessingException{
        try {
            String payload = new ObjectMapper().writeValueAsString(
                Map.of("nome", "")
            );
            System.out.println(payload);
            mvc.perform(post("/api/v1/categorias")
            .contentType("application/json")
            .content(payload))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deveRetornar400_QuandoExistirCategoria() throws JsonProcessingException{
        try {
            String payload = new ObjectMapper().writeValueAsString(
                Map.of("nome", "Cat2")
            );
            
            mvc.perform(post("/api/v1/categorias")
            .contentType("application/json")
            .content(payload));

            //Asertion categoria duplicada
            mvc.perform(post("/api/v1/categorias")
            .contentType("application/json")
            .content(payload))
            .andExpect(MockMvcResultMatchers.status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
