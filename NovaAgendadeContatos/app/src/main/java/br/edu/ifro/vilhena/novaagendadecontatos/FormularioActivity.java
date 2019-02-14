package br.edu.ifro.vilhena.novaagendadecontatos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private Button formularioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formularioBtn = findViewById(R.id.formulario_btn);

        formularioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormularioActivity.this, "Contato Salvo con Sucesso", Toast.LENGTH_LONG).show();
            }
        });
    }
}
