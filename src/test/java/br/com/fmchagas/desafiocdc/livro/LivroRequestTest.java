package br.com.fmchagas.desafiocdc.livro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fmchagas.desafiocdc.autor.Autor;
import br.com.fmchagas.desafiocdc.categoria.Categoria;

public class LivroRequestTest{
    private LivroRequest request = new LivroRequest("","","",
    BigDecimal.TEN, 100, "",LocalDate.now(), 1l ,1l);
    
    @Test
    void deveCriarLivro_QuandoCategoriaEAutorCadastrado(){
        EntityManager manager = Mockito.mock(EntityManager.class);
        Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(new Categoria("teste"));
        Mockito.when(manager.find(Autor.class, 1l)).thenReturn(new Autor("nome", "email", "descricao"));

        Assertions.assertNotNull(request.toModel(manager));
    }

    @Test
    void naoDeveCriarLivro_QuandoAutorNaoExitir(){
        EntityManager manager = Mockito.mock(EntityManager.class);
        Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(new Categoria("teste"));
        Mockito.when(manager.find(Autor.class, 1l)).thenReturn(null);

        Assertions.assertThrows(IllegalStateException.class, ()->{
            request.toModel(manager);
        });
    }

    @Test
    void naoDeveCriarLivro_QuandoCategoriaNaoExitir(){
        EntityManager manager = Mockito.mock(EntityManager.class);
        Mockito.when(manager.find(Categoria.class, 1l)).thenReturn(null);
        Mockito.when(manager.find(Autor.class, 1l)).thenReturn(new Autor("nome", "email", "descricao"));

        Assertions.assertThrows(IllegalStateException.class, ()->{
            request.toModel(manager);
        });
    }
}