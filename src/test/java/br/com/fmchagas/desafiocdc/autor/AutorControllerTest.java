package br.com.fmchagas.desafiocdc.autor;

import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class AutorControllerTest {

    @Autowired
    private MockMvc mvc;
    
    @Test
    void deveCadastrarAutor_QuandoDadosForemValidos(){
        try {
            String payload = new ObjectMapper()
            .writeValueAsString(
                Map.of("nome", "Fernando",
                "email", "fer@gmail.com",
                "descricao", "Descrição autor")
            );

            MockHttpServletRequestBuilder conteudo = 
            MockMvcRequestBuilders.post("/api/v1/autores")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON_VALUE);

            mvc.perform(conteudo)
            .andExpect(MockMvcResultMatchers.status().isCreated());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void naoDeveCadastrarAutor_QuandoEmailForRepetido(){
        try {
            String payload = new ObjectMapper()
            .writeValueAsString(
                Map.of("nome", "Fernando M",
                "email", "outroautor@gmail.com",
                "descricao", "Descrição autor")
            );

            MockHttpServletRequestBuilder conteudo = 
            MockMvcRequestBuilders.post("/api/v1/autores")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON_VALUE);

            mvc.perform(conteudo)
            .andExpect(MockMvcResultMatchers.status().isCreated());

            mvc.perform(conteudo)
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void naoDeveCadastrarAutor_QuandoDadosInvalidos(){
        try {
            String payload = new ObjectMapper()
            .writeValueAsString(
                Map.of("nome", "",
                "email", "g@gmail",
                "descricao", "Descrição autor")
            );

            MockHttpServletRequestBuilder conteudo = 
            MockMvcRequestBuilders.post("/api/v1/autores")
            .content(payload)
            .contentType(MediaType.APPLICATION_JSON_VALUE);
            
            mvc.perform(conteudo)
            .andExpect(MockMvcResultMatchers.status().isBadRequest());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
