package br.com.romariomenezes.trabalhoandroidlistaalunos.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Romário Menezes on 12/06/2018.
 */

public interface AlunoAPI {

    @GET("/aluno/{rg}")
    Call<Aluno> findAluno(@Path("rg") String rg);

    @POST("/aluno/")
    Call<Void> salvar(@Body Aluno aluno);

    @PUT("/aluno/")
    Call<Void> editar(@Body Aluno aluno);

    @GET("/aluno/")
    Call<List<Aluno>> findAll();

    @DELETE("/aluno/rg/{rg}")
    Call<ResponseBody> deletar(@Path("rg") String rg);

    @GET("/aluno/nome/{nome}/senha/{senha}")
    Call<Aluno> login(@Path("nome") String nome, @Path("senha") String senha);
}
