package com.erickcasav.informacioncontacto;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.icu.text.RelativeDateTimeFormatter;
import android.media.Image;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    static final int DATE_DIALOG_ID = 100;
    private int anio;
    private int mes;
    private int dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = getIntent().getExtras();

        //Valida si existen los parametros de vuelta
        if (parametros != null){
            String nombre = parametros.getString("nombre");
            String fecha = parametros.getString("fecha");
            String telefono = parametros.getString("telefono");
            String email = parametros.getString("email");
            String descripcion = parametros.getString("descripcion");

            EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
            TextView tvFecha = (TextView) findViewById(R.id.tvFecha);
            EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);
            EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
            EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

            txtNombre.setText(nombre);
            tvFecha.setText(fecha);
            txtTelefono.setText(telefono);
            txtEmail.setText(email);
            txtDescripcion.setText(descripcion);

            DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

            try{
                Date fechaTemp =(Date)formato.parse(fecha);

                Calendar calendarTemp = Calendar.getInstance();
                calendarTemp.setTime(fechaTemp);

                anio = calendarTemp.get(Calendar.YEAR);
                mes = calendarTemp.get(Calendar.MONTH);
                dia = calendarTemp.get(Calendar.DAY_OF_MONTH);
            }
            catch(ParseException e){
                e.printStackTrace();
            }
        }
        else
        {
            final Calendar calendar = Calendar.getInstance();
            anio = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
        }


        //ImageView ivFechaNacimiento = (ImageView) findViewById(R.id.ivFechaNacimiento);
        //DatePicker dpFechaNacimiento = (DatePicker) findViewById(R.id.dpFecha);


        //dpFechaNacimiento.init(anio, mes, dia, null);

        Button btnSiguiente = (Button) findViewById(R.id.btnSiguiente);

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
                TextView tvFecha = (TextView) findViewById(R.id.tvFecha);
                EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);
                EditText txtEmail = (EditText) findViewById(R.id.txtEmail);
                EditText txtDescripcion = (EditText) findViewById(R.id.txtDescripcion);

                Intent intent = new Intent(MainActivity.this, ConfirmarDatosActivity.class);

                intent.putExtra("nombre", txtNombre.getText().toString());
                intent.putExtra("fecha", tvFecha.getText());
                intent.putExtra("telefono", txtTelefono.getText().toString());
                intent.putExtra("email", txtEmail.getText().toString());
                intent.putExtra("descripcion", txtDescripcion.getText().toString());

                startActivity(intent);

                finish();
            }
        });
    }


    public void asignaFecha(View view) {
        showDialog(DATE_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id ==DATE_DIALOG_ID){
            return new DatePickerDialog(this, datePickerListener, anio, mes, dia);
        }

        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int seleccionAnio, int seleccionMes, int seleccionDia) {

            muestraFecha(seleccionDia, seleccionMes, seleccionAnio);

        }
    };

    private void muestraFecha(int dia, int mes, int anio)
    {
        TextView tvFecha = (TextView) findViewById(R.id.tvFecha);

        tvFecha.setText(new StringBuilder().append(dia).append("/").append(mes).append("/").append(anio));
    }
}
