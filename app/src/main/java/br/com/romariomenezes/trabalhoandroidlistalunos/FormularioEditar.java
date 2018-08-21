package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.romariomenezes.services.AlunoServices;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.Aluno;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.AlunoAPI;
import br.com.romariomenezes.utils.MaskEditUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FormularioEditar extends AppCompatActivity {

    private Aluno aluno;
    private TextView tvNome;
    private EditText tvDataNascimento;
    private EditText tvCpf;
    private EditText tvTelefone;
    private TextView tvEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_editar);

        aluno = (Aluno) getIntent().getSerializableExtra("aluno");

        tvNome = findViewById(R.id.formulario_editar_nome);

        tvDataNascimento = findViewById(R.id.formulario_editar_data_nascimento);
        tvDataNascimento.addTextChangedListener(MaskEditUtil.mask(tvDataNascimento, MaskEditUtil.FORMAT_DATE));

        tvCpf = findViewById(R.id.formulario_editar_cpf);
        tvCpf.addTextChangedListener(MaskEditUtil.mask(tvCpf, MaskEditUtil.FORMAT_CPF));

        tvTelefone = findViewById(R.id.formulario_editar_telefone);
        tvTelefone.addTextChangedListener(MaskEditUtil.mask(tvTelefone, MaskEditUtil.FORMAT_FONE));

        tvEmail = findViewById(R.id.formulario_editar_email);

        if(aluno != null){
            tvNome.setText(aluno.getNome());
            tvDataNascimento.setText(aluno.getDataNascimento());
            tvCpf.setText(aluno.getCpf());
            tvTelefone.setText(aluno.getTelefone());
            tvEmail.setText(aluno.getEmail());
        }

        Button botaoCdastrar = findViewById(R.id.formulario_editar_salvar);
        botaoCdastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editar(view, aluno);
                Toast.makeText(FormularioEditar.this, "Editar Aluno", Toast.LENGTH_SHORT ).show();
                finish();
            }
        });

        Button botaoCancelar = findViewById(R.id.formulario_editar_cancelar);
        botaoCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public void editar(View v, Aluno aluno2) {
        AlunoAPI alunoAPI = new AlunoServices().getRetrofit().create(AlunoAPI.class);

        Aluno aluno = new Aluno();
        aluno.setNome(tvNome.getText().toString());
        aluno.setDataNascimento(tvDataNascimento.getText().toString());
        aluno.setRg(aluno2.getRg());
        aluno.setCpf(tvCpf.getText().toString());
        aluno.setTelefone(tvTelefone.getText().toString());
        aluno.setEmail(tvEmail.getText().toString());

        Call<Void> call = alunoAPI.editar(aluno);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(FormularioEditar.this, "Aluno alterado", Toast.LENGTH_SHORT ).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(FormularioEditar.this, "Falha", Toast.LENGTH_SHORT ).show();
            }
        });
    }

    private Retrofit getRetrofit(){
        return new Retrofit.Builder().baseUrl("https://androidmba.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create()).build();
    }

}
