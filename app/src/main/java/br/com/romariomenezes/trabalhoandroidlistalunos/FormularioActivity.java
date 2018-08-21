package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.romariomenezes.trabalhoandroidlistaalunos.model.Aluno;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.AlunoAPI;
import br.com.romariomenezes.utils.MaskEditUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormularioActivity extends AppCompatActivity {

    private TextView tvNome;
    private EditText tvRg;
    private EditText tvDataNascimento;
    private EditText tvCpf;
    private EditText tvTelefone;
    private TextView tvEmail;
    private  RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        //adapter = (RecycleViewAdapter) getIntent().getSerializableExtra("adapter");
        tvNome = findViewById(R.id.formulario_nome);

        tvRg = findViewById(R.id.formulario_rg);
        tvRg.addTextChangedListener(MaskEditUtil.mask(tvRg, MaskEditUtil.FORMAT_RG));

        tvDataNascimento = findViewById(R.id.formulario_data_nascimento);
        tvDataNascimento.addTextChangedListener(MaskEditUtil.mask(tvDataNascimento, MaskEditUtil.FORMAT_DATE));

        tvCpf = findViewById(R.id.formulario_cpf);
        tvCpf.addTextChangedListener(MaskEditUtil.mask(tvCpf, MaskEditUtil.FORMAT_CPF));

        tvTelefone = findViewById(R.id.formulario_telefone);
        tvTelefone.addTextChangedListener(MaskEditUtil.mask(tvTelefone, MaskEditUtil.FORMAT_FONE));

        tvEmail = findViewById(R.id.formulario_email);

        Button botaoCdastrar = findViewById(R.id.formulario_cadastrar);
        botaoCdastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    salvar(view);


            }
        });

        Button botaoCancelar = findViewById(R.id.formulario_cad_cancelar);
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void salvar(View v){
        AlunoAPI alunoAPI = getRetrofit().create(AlunoAPI.class);

        Aluno aluno = new Aluno();
        aluno.setNome(tvNome.getText().toString());

        String rg = tvRg.getText().toString();
        rg = rg.replace(".","");
        rg = rg.replaceAll("-","");
        aluno.setRg(rg);

        aluno.setDataNascimento(tvDataNascimento.getText().toString());
        aluno.setCpf(tvCpf.getText().toString());
        aluno.setTelefone(tvTelefone.getText().toString());
        aluno.setEmail(tvEmail.getText().toString());

        String nome = tvNome.getText().toString();

        if(nome == null || nome.trim().length() == 0){
            Toast.makeText(FormularioActivity.this, "Obrigatorio o nome do Aluno", Toast.LENGTH_LONG).show();
        }else if(rg == null || rg.trim().length() == 0){
            Toast.makeText(FormularioActivity.this, "Obrigatorio o  RG", Toast.LENGTH_LONG).show();
        }else {

            alunoAPI.salvar(aluno).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Toast.makeText(FormularioActivity.this, "Gravado com sucesso",
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });
        }
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder().baseUrl("https://androidmba.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }
}


