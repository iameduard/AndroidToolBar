package com.example.iamed.miscontactos;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactoAdaptador extends RecyclerView.Adapter<ContactoAdaptador.ContactoViewHolder> {

    ArrayList<Contacto> contactos;
    Activity activity;

    public ContactoAdaptador(ArrayList<Contacto> contactos,Activity activity){

        this.contactos=contactos;
        this.activity=activity;
    }

    //Inflar el layout y lo pasara al viewholder para que obtenga los views..
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_contacto,viewGroup,false);
        return new ContactoViewHolder(v);
    }

    //Asocia cada elemento de la lista con cada view
    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder contactoViewHolder, int i) {
        final Contacto contacto=contactos.get(i);
        contactoViewHolder.imgFoto.setImageResource(contacto.getFoto());
        contactoViewHolder.tvNombreCV.setText(contacto.getNombre());
        contactoViewHolder.tvTelefonoCV.setText(contacto.getTelefono());


        contactoViewHolder.imgFoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, contacto.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity,DetalleContacto.class);
                intent.putExtra("nombre",contacto.getNombre());
                intent.putExtra("telefono",contacto.getTelefono());
                intent.putExtra("email",contacto.getEmail());
                activity.startActivity(intent);

            }
        });

        contactoViewHolder.btnLike.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity,"Diste Like a: "+contacto.getNombre(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public static class ContactoViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombreCV;
        private TextView tvTelefonoCV;
        private ImageButton btnLike;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto=(ImageView) itemView.findViewById(R.id.imgFoto);
            tvNombreCV=(TextView) itemView.findViewById(R.id.tvNombreCV);
            tvTelefonoCV=(TextView) itemView.findViewById(R.id.tvTelefonoCV);
            btnLike = (ImageButton) itemView.findViewById(R.id.btnLike);
        }
    }
}
