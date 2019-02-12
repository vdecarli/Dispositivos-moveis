package br.edu.ifro.vilhena.agendadecontatos;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import br.edu.ifro.vilhena.agendadecontatos.dao.ContatoDAO;
import br.edu.ifro.vilhena.agendadecontatos.model.Contato;

public class ListarContatosActivity extends AppCompatActivity {

   private ListView listarContatosListview;
   private FloatingActionButton listatContatosBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_contatos);

        //mapeamento do componente da active

        listarContatosListview = findViewById(R.id.listar_contatos_Listview);
        listatContatosBtn = findViewById(R.id.listar_contatos_btn);



        // ao clicar floatActionButton
        listatContatosBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListarContatosActivity.this,
                        FormularioActivity.class);
                            startActivity(intent);

            }
        });
    }
    protected void onResume(){
        super.onResume();
        // String[] contatos = {"Maria da Silva", "Jose Oliveira", "Tereza Costa"};

        ContatoDAO contatoDAO = new ContatoDAO(this);
        List<Contato> contatos = contatoDAO.listar();

        ArrayAdapter<Contato> adpter = new ArrayAdapter<Contato>(this, android.R.layout.simple_list_item_1, contatos);

        listarContatosListview.setAdapter(adpter);
    }
}
