package br.edu.ifro.vilhena.novaagendadecontatos;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.ifro.vilhena.novaagendadecontatos.dao.ContatoDAO;
import br.edu.ifro.vilhena.novaagendadecontatos.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    private Button formularioBtn;
    private TextInputEditText formularioNome;
    private TextInputEditText formularioEmail;
    private TextInputEditText formularioTelefone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formularioBtn = findViewById(R.id.formulario_btn);
        formularioNome = findViewById(R.id.formulario_nome);
        formularioEmail = findViewById(R.id.formulario_email);
        formularioTelefone = findViewById(R.id.formulario_telefone);
        //funsao caregar o botão
        formularioBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //criando o objeto para incerir
                Contato contato = new Contato();
                contato.setNome(formularioNome.getText().toString());
                contato.setEmail(formularioEmail.getText().toString());
                contato.setTelefone(formularioTelefone.getText().toString());

                ContatoDAO contatoDAO = new ContatoDAO(FormularioActivity.this);
                contatoDAO.inserir(contato);

                contatoDAO.close();

                Toast.makeText(FormularioActivity.this, "Contato Salvo con Sucesso", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }
}
