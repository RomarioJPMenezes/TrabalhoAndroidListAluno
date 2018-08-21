package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.romariomenezes.services.AlunoServices;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.User;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.UserAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity  {

    private EditText tvEmail;
    private EditText tvSenha;
    private EditText tvSenhaConfirm;
    private Button btCadastrar;
    private Button btCancelar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_registro);

        tvEmail = (EditText) findViewById(R.id.formulario_registro_email);
        tvSenha = (EditText) findViewById(R.id.formulario_registro_senha);
        tvSenhaConfirm = (EditText) findViewById(R.id.formulario_registro_senha_confirm);
        btCadastrar = (Button) findViewById(R.id.formulario_registro_cadastrar);
        btCancelar = (Button)  findViewById(R.id.formulario_registro_cancelar);

        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvSenha.getText().toString().equals(tvSenhaConfirm.getText().toString())){
                    salvar(view);
                    finish();
                }else{
                    Toast.makeText(RegistroActivity.this, "Senha não confirmada!",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }

    public void salvar(View v){
        UserAPI userAPI = new AlunoServices().getRetrofit().create(UserAPI.class);

        User user = new User();
        user.setEmail(tvEmail.getText().toString());
        user.setSenha(tvSenha.getText().toString());

        userAPI.salvar(user).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(RegistroActivity.this, "Gravado com sucesso",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(RegistroActivity.this, "Erro ao cadastrar novo Usuario",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}