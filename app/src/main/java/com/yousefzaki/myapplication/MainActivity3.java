package com.yousefzaki.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.DataSetObserver;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mumayank.com.airlocationlibrary.AirLocation;


public class MainActivity3 extends AppCompatActivity {
    AirLocation airLocation;
    ArrayList<String> stations = new ArrayList<>();
    ArrayList<Double> latstations = new ArrayList<>();
    ArrayList<Double> longstations = new ArrayList<>();
    AutoCompleteTextView alerttext;
    Boolean arrive=false;
    long delay=1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        alerttext=findViewById(R.id.textalert);
        Collections.addAll(stations, "المرج الجديدة","المرج","عزبة النخل","عين شمس", "مطرية", "حلمية الزيتون", "حدائق الزيتون", "سراي القبة", "حمامات القبة", "كوبري القبة", "منشية الصدر", "الدمرداش", "غمرة", "الشهداء", "احمد عرابي", "جمال عبد الناصر", "السادات", "سعد زغلول", "السيدة زينب", "الملك الصالح", "مار جرجس", "الزهراء", "دار السلام", "حدائق المعادي", "المعادي", "ثكانات المعادي", "طره البلد", "كوسيكا", "طره الاسمنت", "المعصرة", "حدائق حلوان", "وادي حوف", "جامعة حلوان", "عين حلوان", "حلوان", "شبرا الخيمة", "كلية الزاعة", "المظلات", "الخلفاوي", "سانت تريز", "روض الفرج", "المسرة", "الشهداء", "العتبة", " محمد نجيب", "السادات", "اوبرا", "الدقي", "البحوث", "جامعة القاهرة", "فيصل ", "الجيزة", "ام المصريين", "ساقية مكي ", "المنيب", "عدلي منصور", "الهايكستيب ", "عمر ابن الخطاب", "قباء", "هشام بركات", "النزهة", "نادي الشمس", "الف المسكن ", "هيليوبلس", "هارون", "الاهرام", "كلية البنات ", "الاستاد", "ارض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية", "العتبة", "جمال عبد الناصر ", "ماسبيرو", "صفاء حجازي", "كيت كات");
        Collections.addAll(latstations, 30.163651, 30.152103, 30.13924, 30.13082, 30.12113, 30.1142, 30.10523, 30.09799, 30.09026, 30.08704, 30.08189, 30.076871, 30.06892, 30.061944, 30.05736, 30.05335, 30.04415, 30.03657, 30.02902, 30.0168, 30.00606, 29.99534, 29.98214, 29.96996, 29.95978, 29.95264, 29.94661, 29.93613, 29.92578, 29.90603, 29.897161, 29.87938, 29.8689, 29.862487, 29.84879, 30.1225, 30.11401, 30.10514, 30.09795, 30.08817, 30.080616, 30.07089, 30.061944, 30.05258, 30.04534, 30.04415, 30.04205, 30.03816, 30.03568, 30.02605, 30.01714, 30.01054, 30.00504, 29.9953, 29.98127, 30.147214, 30.143853, 30.14023, 30.1346, 30.13128, 30.12838, 30.12225, 30.11818, 30.10778, 30.10083, 30.09121, 30.08387, 30.07288, 30.073256, 30.071985, 30.06503, 30.06198, 30.05388, 30.05237, 30.0537, 30.0557, 30.06236, 30.06692);
        Collections.addAll(longstations, 31.338366, 31.335733, 31.32509, 31.31962, 31.31568, 31.3146, 31.31053, 31.30538, 31.29861, 31.29438, 31.28898, 31.276768, 31.26511, 31.246111, 31.24331, 31.23922, 31.23606, 31.23872, 31.23594, 31.2317, 31.23018, 31.2319, 31.24265, 31.25109, 31.25831, 31.26395, 31.27428, 31.28207, 31.28827, 31.30005, 31.303938, 31.31391, 31.32085, 31.324844, 31.33468, 31.244722, 31.24913, 31.24719, 31.24584, 31.24595, 31.245407, 31.24547, 31.246111, 31.24709, 31.24488, 31.246111, 31.22581, 31.21255, 31.20077, 31.20175, 31.20452, 31.20762, 31.20889, 31.20901, 31.21223, 31.427250, 31.404701, 31.39457, 31.38437, 31.37395, 31.36064, 31.34446, 31.34027, 31.33872, 31.33334, 31.32703, 31.32984, 31.31794, 31.300966, 31.283386, 31.27564, 31.26734, 31.25662, 31.24752, 31.23889, 31.23266, 31.22307, 31.2135);
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stations);
        alerttext.setAdapter(a);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        airLocation.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void viewmap(View view) {
        Intent a= new Intent(this,mapview.class);
        startActivity(a);
    }

    public void viewmain(View view) {
        Intent a= new Intent(this,MainActivity.class);
        startActivity(a);
    }








    public void viewtrain(View view) {
        Intent a= new Intent(this,MainActivity.class);
        startActivity(a);
    }

    public void alertme(View view) {






    }
}
