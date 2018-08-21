package br.com.romariomenezes.trabalhoandroidlistalunos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Romário Menezes on 03/08/2018.
 */


public class HolderRecycleView  extends RecyclerView.ViewHolder {

    final TextView nome;
    final ImageButton deleteButton;
    final ImageButton moreButton;

    public HolderRecycleView(View view) {
        super(view);
        nome = (TextView) view.findViewById(R.id.item_nome);
        moreButton =  view .findViewById(R.id.main_line_more);
        deleteButton = view .findViewById(R.id.main_line_delete);

    }

}
