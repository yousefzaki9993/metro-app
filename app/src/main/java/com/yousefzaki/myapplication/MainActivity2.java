package com.yousefzaki.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.logging.LogRecord;

public class MainActivity2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                Intent a= new Intent(MainActivity2.this,MainActivity.class);
                startActivity(a);
            }
        };
        Handler handler=new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable,3000);
    }

//Collections.addAll(line1, "New El Marg", "El Marg", "Ezbet El-Nakhl", "Ain Shams", "Mattareya", "Helmeyet El Zaytoun", "Hadayek El Zaytoun", "Saray El-Qobba", "Hammamat El-Qobba", "Kobri El-Qobba", "Manshiet El-Sadr", "El-Demerdash", "Ghamra", "Al-Shohadaa", "ahmed oraby", "Nasser", "Sadat", "Saad Zaghloul", "Sayyeda Zeinab", "El Malek El Saleh", "Mar Girgis", "El-Zahraa", "Dar El-Salam", "Hadayeq El Maadi", "El Maadi", "Sakanat El Maadi", "Tura El Balad", "Kozzika", "Tura El Asmant", "El Maasara", "Hadayek Helwan", "Wadi Hof", "Helwan University", "Ain Helwan", "Helwan");
//        Collections.addAll(line2, "Shubra El Kheima", "kolleyyet El-zeraa", "El Mezallat", "El Khalafawy", "Saint Threse", "Rod El Farag", "Massara", "Al-Shohadaa", "Attaba", " Nageeb", "Sadat", "Opera", "Dokki", "El Bohoos", "Cairo University", "Faysal", "Giza", "Om El Masryeen", "Sakyet Mekky", "Al Moneeb");
//        Collections.addAll(line3, "Adly Mansour", "El Haykestep ", "Omar Ibn Alkhattab", "Qubaa", "Hesham Barakat", "El-Nozha", "El-Shams Club", "Alf Maskan", "Heliopolis", "Haroun", "Elahram", "Kolleyet El Banat", "Stadium", "Fair Zone", "Abbassia", "Abdu Basha", "El Geish", "Bab El Shaariya", "Attaba", "Nasser", "Maspero", "Safaa Hegazy", "Kit Kat");


}