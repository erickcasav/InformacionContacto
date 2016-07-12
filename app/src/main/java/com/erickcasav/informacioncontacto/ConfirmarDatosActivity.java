package com.erickcasav.informacioncontacto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmarDatosActivity extends AppCompatActivity {


    private String nombre;
    private String fecha;
    private String telefono;
    private String email;
    private String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_datos);

        Bundle parametros = getIntent().getExtras();

        nombre = parametros.getString("nombre");
        fecha = parametros.getString("fecha");
        telefono = parametros.getString("telefono");
        email = parametros.getString("email");
        descripcion = parametros.getString("descripcion");

        TextView tvNombreConfirmar = (TextView) findViewById(R.id.tvNombreConfirmar);
        TextView tvFechaConfirmar = (TextView) findViewById(R.id.tvFechaConfirmar);
        TextView tvTelefono = (TextView) findViewById(R.id.tvTelefonoConfirmar);
        TextView tvEmail = (TextView) findViewById(R.id.tvEmailConfirmar);
        TextView tvDescriocion = (TextView) findViewById(R.id.tvDescripcionConfirmar);

        tvNombreConfirmar.setText(nombre);
        tvFechaConfirmar.setText(fecha);
        tvTelefono.setText(telefono);
        tvEmail.setText(email);
        tvDescriocion.setText(descripcion);

        Button btnConfirmarDatos = (Button) findViewById(R.id.btnConfirmarDatos);

        btnConfirmarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConfirmarDatosActivity.this, MainActivity.class);

                intent.putExtra("nombre", nombre);
                intent.putExtra("fecha", fecha);
                intent.putExtra("telefono", telefono);
                intent.putExtra("email", email);
                intent.putExtra("descripcion", descripcion);

                startActivity(intent);

                finish();
            }
        });

    }
}
