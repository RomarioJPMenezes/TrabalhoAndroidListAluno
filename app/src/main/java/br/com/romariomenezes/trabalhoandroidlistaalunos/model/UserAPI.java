package br.com.romariomenezes.trabalhoandroidlistaalunos.model;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Romário Menezes on 12/06/2018.
 */

public interface UserAPI {


    @POST("/user/")
    Call<Void> salvar(@Body User user);

    @PUT("/user/")
    Call<Void> editar(@Body Aluno user);

    @GET("/user/")
    Call<List<Aluno>> findAll();

    @DELETE("/user/email/{email}")
    Call<ResponseBody> deletar(@Path("user") String rg);

    @GET("/user/email/{email}/senha/{senha}")
    Call<User> login(@Path("email") String email, @Path("senha") String senha);
}
