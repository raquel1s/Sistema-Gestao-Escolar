package com.weg.gestao_escola.util;

public class GerarIn {

    public static String gerando(int size){
        StringBuilder sql = new StringBuilder("(");

        for(int i = 0; i < size; i++){
            sql.append("?");
            if(i < size - 1){
                sql.append(", ");
            }
        }
        sql.append(")");
        return sql.toString();
    }
}
