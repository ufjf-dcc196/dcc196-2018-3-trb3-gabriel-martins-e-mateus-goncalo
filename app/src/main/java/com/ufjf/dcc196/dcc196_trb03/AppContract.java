package com.ufjf.dcc196.dcc196_trb03;

import android.provider.BaseColumns;

public class AppContract {

    public final class Lista implements BaseColumns {
        public final static String TABLE_NAME = "Lista";
        public final static String COLUMN_NAME_REGISTRO = "registro";
        public final static String COLUMN_NAME_NOME = "nome";
        public final static String COLUMN_NAME_DATA = "data";
        public static final String COLUMN_NAME_VALOR = "valor";
        public static final String COLUMN_NAME_MERCADO = "nomeMercado";
        public static final String COLUMN_NAME_EHCOMPRA = "ehCompra";
        public final static String CREATE_LISTA  = "CREATE TABLE "+Lista.TABLE_NAME+" ("
                + Lista.COLUMN_NAME_REGISTRO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Lista.COLUMN_NAME_NOME+ " TEXT, "
                + Lista.COLUMN_NAME_DATA+ " TEXT, "
                + Lista.COLUMN_NAME_VALOR+ " REAL, "
                + Lista.COLUMN_NAME_MERCADO+ " TEXT, "
                + Lista.COLUMN_NAME_EHCOMPRA+ " INTEGER"
                +")";
        public final static String DROP_LISTA = "DROP TABLE IF EXISTS "+Lista.TABLE_NAME;
    }

    public final class ItemLista implements BaseColumns {
        public final static String TABLE_NAME = "ItemLista";
        public final static String COLUMN_NAME_REGISTRO = "registro";
        public final static String COLUMN_NAME_NOME = "nome";
        public final static String COLUMN_NAME_QUANTIDADE = "quantidade";
        public final static String COLUMN_NAME_VALOR = "valor";
        public final static String COLUMN_NAME_LISTA = "registroLista";
        public final static String CREATE_ITEMLISTA  = "CREATE TABLE "+ ItemLista.TABLE_NAME+" ("
                + ItemLista.COLUMN_NAME_REGISTRO + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ItemLista.COLUMN_NAME_NOME+ " TEXT, "
                + ItemLista.COLUMN_NAME_QUANTIDADE+ " INTEGER, "
                + ItemLista.COLUMN_NAME_VALOR+ " REAL, "
                + ItemLista.COLUMN_NAME_LISTA+ " INTEGER"
                +")";
        public final static String DROP_ITEMLISTA = "DROP TABLE IF EXISTS "+ ItemLista.TABLE_NAME;
    };

}
