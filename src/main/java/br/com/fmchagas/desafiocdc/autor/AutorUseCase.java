package br.com.fmchagas.desafiocdc.autor;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

@Service //3
public class AutorUseCase {
    //1
    private AutorRepositoryGatway autorRepositoryGatway;
    
    public AutorUseCase(AutorRepositoryGatway autorRepositoryGatway){
        this.autorRepositoryGatway = autorRepositoryGatway;
    }

    //1
    public Autor execute(@Valid AutorRequest request){
        //1
        Autor autor = request.toModel();

		return autorRepositoryGatway.save(autor);
    }
}
