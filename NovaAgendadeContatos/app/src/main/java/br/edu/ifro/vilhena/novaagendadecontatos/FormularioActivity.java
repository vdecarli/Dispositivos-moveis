package br.edu.ifro.vilhena.novaagendadecontatos;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import br.edu.ifro.vilhena.novaagendadecontatos.dao.ContatoDAO;
import br.edu.ifro.vilhena.novaagendadecontatos.model.Contato;

public class FormularioActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 457;

    private Button formularioBtn;
    private TextInputEditText formularioNome;
    private TextInputEditText formularioEmail;
    private TextInputEditText formularioTelefone;
    private Contato contato;

    private FloatingActionButton formularioBtnFoto;
    private ImageView formularioFoto;
    private String caminhoFoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        formularioBtn = findViewById(R.id.formulario_btn);
        formularioNome = findViewById(R.id.formulario_nome);
        formularioEmail = findViewById(R.id.formulario_email);
        formularioTelefone = findViewById(R.id.formulario_telefone);
        formularioBtnFoto = findViewById(R.id.formulario_btn_foto);
        formularioFoto = findViewById(R.id.formulario_foto);

        formularioBtnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/foto" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivoFoto));

                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());

                startActivityForResult(intent, CODIGO_CAMERA);

            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra("contato")) {
            contato = (Contato) intent.getSerializableExtra("contato");
            formularioBtn.setText("Alterar");

        } else {
            contato = new Contato();
        }

        if (contato != null) {
            formularioNome.setText(contato.getNome());
            formularioTelefone.setText(contato.getTelefone());
            formularioEmail.setText(contato.getEmail());

            carregarimagem(contato.getCaminhoFoto());
        }


        //funsao caregar o botão
        formularioBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //criando o objeto para incerir

                contato.setNome(formularioNome.getText().toString());
                contato.setEmail(formularioEmail.getText().toString());
                contato.setTelefone(formularioTelefone.getText().toString());
                contato.setCaminhoFoto((String) formularioFoto.getTag());

                ContatoDAO contatoDAO = new ContatoDAO(FormularioActivity.this);

                if (contato.getId() != 0) {
                    contatoDAO.alterar(contato);
                } else {

                    contatoDAO.inserir(contato);
                }

                contatoDAO.close();

                Toast.makeText(FormularioActivity.this, "Contato Salvo con Sucesso", Toast.LENGTH_LONG).show();

                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == Activity.RESULT_OK) {

            if (requestCode == CODIGO_CAMERA) {
                carregarimagem(caminhoFoto);
            }
        }
    }

    private void carregarimagem(String caminhoFoto) {

        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
        if (bitmap != null) {
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            formularioFoto.setImageBitmap(bitmapReduzido);
            formularioFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            formularioFoto.setTag(caminhoFoto);
        }
    }
}
