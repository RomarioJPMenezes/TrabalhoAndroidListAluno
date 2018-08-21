package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.com.romariomenezes.services.AlunoServices;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.Aluno;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.AlunoAPI;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.User;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.UserAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    Button btnRegistrar;
    UserAPI userAPI;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = (EditText) findViewById(R.id.edtUsername);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        userAPI = new AlunoServices().getRetrofit().create(UserAPI.class);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFormulario = new Intent(LoginActivity.this, RegistroActivity.class);
                startActivity(goToFormulario);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                //validate form
                if(validateLogin(username, password)){
                    //do login
                    doLogin(username, password);
                }
            }
        });

    }

    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void doLogin(final String username,final String password){
        Call call = userAPI.login(username,password);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.isSuccessful()){
                    user = (User)response.body();
                    if(user.getEmail().toUpperCase().equals(edtUsername.getText().toString().toUpperCase().trim()) &&
                            user.getSenha().toUpperCase().equals(edtPassword.getText().toString().toUpperCase().trim())){

                        CheckBox checkBox = findViewById(R.id.logcheckbox);
                        if(checkBox.isChecked()) {
                            SharedPreferences pref = getSharedPreferences("info", MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("user", user.getEmail());
                            editor.commit();
                        }
                        Intent intent = new Intent(LoginActivity.this, MenuPrincipal.class);
                        intent.putExtra("user", user);

                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuário ou Senha está incorreto"+user.getEmail()+" "+user.getSenha(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Error! Tente novamente!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Usuário ou Senha está incorreto", Toast.LENGTH_SHORT).show();
            }
        });
    }


}