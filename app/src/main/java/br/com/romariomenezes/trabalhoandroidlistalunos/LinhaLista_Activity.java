package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.romariomenezes.services.AlunoServices;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.Aluno;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.AlunoAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LinhaLista_Activity extends AppCompatActivity {

    private ListView listaAlunosAtivicty;
    private List<Aluno> alunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linha_lista_);



    }
}
