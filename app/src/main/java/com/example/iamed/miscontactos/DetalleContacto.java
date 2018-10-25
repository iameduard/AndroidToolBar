package com.example.iamed.miscontactos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import static android.content.Intent.ACTION_SEND;
import static android.net.Uri.parse;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvNombre;
    private TextView tvTelefono;
    private TextView tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        Bundle parametros=getIntent().getExtras();
        String nombre   = parametros.getString(getResources().getString(R.string.pnombre));
        String telefono = parametros.getString(getResources().getString(R.string.ptelefono));
        String email    = parametros.getString(getResources().getString(R.string.pemail));

        tvNombre        =(TextView) findViewById(R.id.tvNombre);
        tvTelefono      =(TextView) findViewById(R.id.tvTelefono);
        tvEmail         =(TextView) findViewById(R.id.tvEmail);

        tvNombre.setText(nombre);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);

    }

    public void llamar(View v){
        String telefono = tvTelefono.getText().toString();
        startActivity(new Intent(Intent.ACTION_CALL, parse("tel:"+telefono)));
    }

    public void enviarMail(View v){
        String email=tvEmail.getText().toString();
        Intent emailIntent= new Intent((ACTION_SEND));
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, email);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Prueba de Email");
        emailIntent.putExtra(Intent.EXTRA_TEXT,"Cuerpo del email");
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent,"Email "));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent =new Intent(DetalleContacto.this,MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onKeyDown(keyCode, event);
    }
}
