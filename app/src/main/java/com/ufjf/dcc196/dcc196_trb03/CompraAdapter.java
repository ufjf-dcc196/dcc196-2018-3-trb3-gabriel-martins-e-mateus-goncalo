package com.ufjf.dcc196.dcc196_trb03;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.ViewHolder>{

    private Cursor cursor;
    private OnListaClickListener listener;
    private OnListaLongClickListener longListener;

    public interface OnListaClickListener {
        void onListaClick(View listaView, int position);
    }
    public interface OnListaLongClickListener {
        void onListaLongClick(View listaView, int position);
    }

    public void setOnCompraClickListener(OnListaClickListener listener){
        this.listener = listener;
    }
    public void setOnCompraLongClickListener(OnListaLongClickListener listener){
        this.longListener = listener;
    }

    public CompraAdapter (Cursor c)
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
        View compraView = inflater.inflate(R.layout.compras_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(compraView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int idxNome = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_NOME);
        int idxData = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_DATA);
        int idxValor = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_VALOR);
        cursor.moveToPosition(i);
        viewHolder.txtNomeCompra.setText(cursor.getString(idxNome));
        viewHolder.txtValorCompra.setText("R$" + String.valueOf(cursor.getDouble(idxValor)));
        viewHolder.txtDataCompra.setText(cursor.getString(idxData));
    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNomeCompra;
        public TextView txtDataCompra;
        public TextView txtValorCompra;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtNomeCompra = itemView.findViewById(R.id.txtCompraNome);
            txtDataCompra = itemView.findViewById(R.id.txtCompraData);
            txtValorCompra = itemView.findViewById(R.id.txtCompraValor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        cursor.moveToPosition(position);
                        Integer idxNum = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_REGISTRO);
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
                        Integer idxNum = cursor.getColumnIndexOrThrow(AppContract.Lista.COLUMN_NAME_REGISTRO);
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