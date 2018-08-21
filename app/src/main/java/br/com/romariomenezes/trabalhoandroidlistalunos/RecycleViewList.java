package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.romariomenezes.services.AlunoServices;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.Aluno;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.AlunoAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleViewList extends AppCompatActivity {

    private SearchView search;
    private RecyclerView recyclerView;
    private Context context;
    private List<Aluno> alunos;
    private RecycleViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_list);

        context = this;
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        RecyclerView.LayoutManager layout = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layout);

        search = findViewById(R.id.searchCamp);
        search.setActivated(true);
        search.setQueryHint(this.getString(R.string.st_pesquisar));
        search.onActionViewExpanded();
        search.setIconified(false);
        search.clearFocus();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        carregarLista();

        FloatingActionButton novoAluno = findViewById(R.id.recycler_view_layour_fab);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToFormulario = new Intent(RecycleViewList.this, FormularioActivity.class);
                startActivity(goToFormulario);

            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        carregarLista();
    }


    public void carregarLista(){
        AlunoAPI alunoAPI = new AlunoServices().getRetrofit().create(AlunoAPI.class);
        Call call = alunoAPI.findAll();
        call.enqueue(new Callback<List<Aluno>>() {
            @Override
            public void onResponse(Call call, Response response) {
                alunos = (List<Aluno>) response.body();
                adapter = new RecycleViewAdapter(alunos, context);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
