package com.example.domingolezcano.volley;

import android.app.DatePickerDialog;
import android.support.annotation.IdRes;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class menu extends DialogFragment {

    public menu() {
        // Required empty public constructor
    }

    private EditText mDatos;
    private Button mAgregar;

    private RadioGroup mRadioGroup;
    private TextView mFecha;
    private TextView mHinicio;
    private TextView mHfin;
    private  int dia, mes, año;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_menu, container, false);
        iniciarHora(view);// Setear hora inicial
        iniciarFecha(view);// Setear fecha inicial
        return view;
    }


    private void iniciarHora(View view) {
        mHinicio = (TextView) view.findViewById(R.id.hincio);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("HH:mm a");
        mHinicio.setText(format.format(c.getTime()));

        mHinicio.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new TimeDialog().show(getFragmentManager(), "TimePickerInFull");
                    }
                }
        );
    }

    private void iniciarFecha(View view) {
        mFecha = (TextView) view.findViewById(R.id.fecha);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("E MMM d yyyy");
        mFecha.setText(format.format(c.getTime()));

        mFecha.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new DateDialog().show(getFragmentManager(), "DatePickerInFull");
                    }
                }
        );
    }

}
