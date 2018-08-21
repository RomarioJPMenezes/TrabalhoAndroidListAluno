package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.romariomenezes.services.AlunoServices;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.Aluno;
import br.com.romariomenezes.trabalhoandroidlistaalunos.model.AlunoAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.app.AppCompatActivity.*;

/**
 * Created by Romário Menezes on 03/08/2018.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter implements Serializable, Filterable {

    ValueFilter valueFilter;
    private LayoutInflater inflater;
    private List<Aluno> alunosListFilter;
    private List<Aluno> alunos;
    private Context context;

    public RecycleViewAdapter(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.alunosListFilter = alunos;
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.activity_linha_lista_, parent, false);

        HolderRecycleView holder = new HolderRecycleView(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        HolderRecycleView holder = (HolderRecycleView) viewHolder;
        Aluno aluno = alunos.get(position);
        String stLinha = context.getString(R.string.st_linha);
        Toast.makeText(context, "Linha: " + stLinha, Toast.LENGTH_SHORT);

        stLinha = "\n" + stLinha.replace("##nome##", aluno.getNome()).
                replace("##cpf##", aluno.getCpf()).
                replace("##rg##", aluno.getRg()).
                replace("##telefone##", aluno.getTelefone()).
                replace("##dataNascimento##", aluno.getDataNascimento()).
                replace("##email##", aluno.getEmail()) + "\n";


        holder.nome.setText(stLinha);


        holder.moreButton.setOnClickListener(view -> updateItem(position));
        holder.deleteButton.setOnClickListener(view -> removerItem(position));
    }

    @Override
    public int getItemCount() {
        return alunos.size();
    }

    /**
     * Método publico chamado para atualziar a lista.
     *
     * @param user Novo objeto que será incluido na lista.
     */
    public void updateList(Aluno user) {
        insertItem(user);
    }

    // Método responsável por inserir um novo usuário na lista e notificar que há novos itens.
    private void insertItem(Aluno user) {
        alunos.add(user);
        notifyItemInserted(getItemCount());
    }

    // Método responsável por atualizar um usuário já existente na lista.
    private void updateItem(int position) {
        Aluno aluno = alunos.get(position);
        Toast.makeText(context, "Editar o aluno : " + aluno.getNome(), Toast.LENGTH_SHORT).show();

        Intent goToFormulario = new Intent(context, FormularioEditar.class);
        goToFormulario.putExtra("aluno", aluno);
        context.startActivity(goToFormulario);
        notifyItemChanged(position);
    }

    // Método responsável por remover um usuário da lista.
    private void removerItem(int position) {
        Aluno aluno = alunos.get(position);
        Toast.makeText(context, "Deletar o aluno : " + aluno.getNome(), Toast.LENGTH_SHORT).show();
        AlunoAPI alunoAPI = new AlunoServices().getRetrofit().create(AlunoAPI.class);
        String rg = aluno.getRg();
        rg = rg.replace(".", "");
        rg = rg.replaceAll("-", "");
        Call call = alunoAPI.deletar(rg);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                alunos.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, alunos.size());
                Toast.makeText(context, "Aluno Deletado.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(context, "Falha ao deletar Aluno", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }


    private class ValueFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            ArrayList<Aluno> listFind = new ArrayList<>();
            if (constraint != null && constraint.length() > 0) {
                for (Aluno aluno : alunosListFilter) {
                    if ((aluno.getNome().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        listFind.add(aluno);
                    }
                }
                results.count = listFind.size();
                results.values = listFind;
            } else {
                results.count = alunosListFilter.size();
                results.values = alunosListFilter;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            alunos = (List<Aluno>) results.values;
            notifyDataSetChanged();
        }

    }

}
