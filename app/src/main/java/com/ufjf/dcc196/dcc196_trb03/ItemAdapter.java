package com.ufjf.dcc196.dcc196_trb03;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder>{

    private Cursor cursor;
    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    public interface OnItemClickListener {
        void onListaClick(View listaView, int position);
    }
    public interface OnItemLongClickListener {
        void onListaLongClick(View listaView, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener listener){
        this.longListener = listener;
    }

    public ItemAdapter (Cursor c)
    {
        cursor = c;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.lista_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        int idxNome = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_NOME);
        int idxQuantidade = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_QUANTIDADE);
        int idxValor = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_VALOR);

        cursor.moveToPosition(i);

        viewHolder.txtItemNome.setText(cursor.getString(idxNome));
        viewHolder.txtQuantidadeItem.setText(String.valueOf(cursor.getInt(idxQuantidade)));
        viewHolder.txtValor.setText("R$" + String.valueOf(cursor.getFloat(idxValor)));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtItemNome;
        public TextView txtQuantidadeItem;
        public TextView txtValor;
        public ViewHolder(final View itemView) {
            super(itemView);
            txtItemNome = itemView.findViewById(R.id.txtItemNome);
            txtQuantidadeItem = itemView.findViewById(R.id.txtQuantidade);
            txtValor = itemView.findViewById(R.id.txtValor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        cursor.moveToPosition(position);
                        Integer idxNum = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_REGISTRO);
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onListaClick(itemView, cursor.getInt(idxNum));
                        }
                    }
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if(longListener!=null){
                        int position = getAdapterPosition();
                        cursor.moveToPosition(position);
                        Integer idxNum = cursor.getColumnIndexOrThrow(AppContract.ItemLista.COLUMN_NAME_REGISTRO);
                        if(position!=RecyclerView.NO_POSITION){
                            longListener.onListaLongClick(itemView, cursor.getInt(idxNum));
                            return true;
                        }
                    }
                    return false;
                }
            });
        }

        @Override
        public void onClick(View v) {
            if(listener!=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    listener.onListaClick(v, position);
                }
            }
        }
        @Override
        public boolean onLongClick(View v) {
            if(longListener!=null){
                int position = getAdapterPosition();
                if(position != RecyclerView.NO_POSITION){
                    longListener.onListaLongClick(v, position);
                    return true;
                }
            }
            return false;
        }
    }
}

