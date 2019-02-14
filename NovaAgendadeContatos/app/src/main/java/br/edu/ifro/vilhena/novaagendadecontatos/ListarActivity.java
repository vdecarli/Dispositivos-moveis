package br.edu.ifro.vilhena.novaagendadecontatos;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

public class ListarActivity extends AppCompatActivity {

    private ListView listarContatos;
    private FloatingActionButton listarAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);

        listarContatos = findViewById(R.id.listar_contatos);
        listarAcao = findViewById(R.id.listar_action);

        String[] contatos = {"Maria", "Jose", "Tereza", "Marcia"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, contatos);

        listarContatos.setAdapter(adapter);

        listarAcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarActivity.this, FormularioActivity.class);
                startActivity(intent);
            }
        });
    }
}
