package com.yousefzaki.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.backup.SharedPreferencesBackupHelper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import mumayank.com.airlocationlibrary.AirLocation;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, AirLocation.Callback {


    AutoCompleteTextView starttext;
    AutoCompleteTextView endtext;
    TextView pathview;
    EditText disttext;
    Button n;
    ArrayList<String> stations = new ArrayList<>();
    ArrayList<Double> latstations = new ArrayList<>();
    ArrayList<Double> longstations = new ArrayList<>();
    ArrayList<String> stations2 = new ArrayList<>();
    ArrayList<String> line1 = new ArrayList<>();
    List<String> line2 = new ArrayList<>();
    List<String> line3 = new ArrayList<>();
    Switch s1;
   TextToSpeech  tts;

//    boolean sound;
   ImageView soundIcon;
    ImageView mapon;

    SharedPreferences pref;
    SharedPreferences textp;
    Location current;
    AirLocation airLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        starttext = findViewById(R.id.starttext);
        mapon=findViewById(R.id.mapon);
        endtext = findViewById(R.id.endtext);
        disttext = findViewById(R.id.disttext);
        pathview = findViewById(R.id.pathview);
        n = findViewById(   R.id.n);
        s1 = findViewById(R.id.switch1);
//        soundIcon = findViewById(R.id.soundIcon);
        Collections.addAll(stations, "المرج الجديدة","المرج","عزبة النخل","عين شمس", "مطرية", "حلمية الزيتون", "حدائق الزيتون", "سراي القبة", "حمامات القبة", "كوبري القبة", "منشية الصدر", "الدمرداش", "غمرة", "الشهداء", "احمد عرابي", "جمال عبد الناصر", "السادات", "سعد زغلول", "السيدة زينب", "الملك الصالح", "مار جرجس", "الزهراء", "دار السلام", "حدائق المعادي", "المعادي", "ثكانات المعادي", "طره البلد", "كوسيكا", "طره الاسمنت", "المعصرة", "حدائق حلوان", "وادي حوف", "جامعة حلوان", "عين حلوان", "حلوان", "شبرا الخيمة", "كلية الزاعة", "المظلات", "الخلفاوي", "سانت تريز", "روض الفرج", "المسرة", "الشهداء", "العتبة", " محمد نجيب", "السادات", "اوبرا", "الدقي", "البحوث", "جامعة القاهرة", "فيصل ", "الجيزة", "ام المصريين", "ساقية مكي ", "المنيب", "عدلي منصور", "الهايكستيب ", "عمر ابن الخطاب", "قباء", "هشام بركات", "النزهة", "نادي الشمس", "الف المسكن ", "هيليوبلس", "هارون", "الاهرام", "كلية البنات ", "الاستاد", "ارض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية", "العتبة", "جمال عبد الناصر ", "ماسبيرو", "صفاء حجازي", "كيت كات");
        Collections.addAll(latstations, 30.163651, 30.152103, 30.13924, 30.13082, 30.12113, 30.1142, 30.10523, 30.09799, 30.09026, 30.08704, 30.08189, 30.076871, 30.06892, 30.061944, 30.05736, 30.05335, 30.04415, 30.03657, 30.02902, 30.0168, 30.00606, 29.99534, 29.98214, 29.96996, 29.95978, 29.95264, 29.94661, 29.93613, 29.92578, 29.90603, 29.897161, 29.87938, 29.8689, 29.862487, 29.84879, 30.1225, 30.11401, 30.10514, 30.09795, 30.08817, 30.080616, 30.07089, 30.061944, 30.05258, 30.04534, 30.04415, 30.04205, 30.03816, 30.03568, 30.02605, 30.01714, 30.01054, 30.00504, 29.9953, 29.98127, 30.147214, 30.143853, 30.14023, 30.1346, 30.13128, 30.12838, 30.12225, 30.11818, 30.10778, 30.10083, 30.09121, 30.08387, 30.07288, 30.073256, 30.071985, 30.06503, 30.06198, 30.05388, 30.05237, 30.0537, 30.0557, 30.06236, 30.06692);
        Collections.addAll(longstations, 31.338366, 31.335733, 31.32509, 31.31962, 31.31568, 31.3146, 31.31053, 31.30538, 31.29861, 31.29438, 31.28898, 31.276768, 31.26511, 31.246111, 31.24331, 31.23922, 31.23606, 31.23872, 31.23594, 31.2317, 31.23018, 31.2319, 31.24265, 31.25109, 31.25831, 31.26395, 31.27428, 31.28207, 31.28827, 31.30005, 31.303938, 31.31391, 31.32085, 31.324844, 31.33468, 31.244722, 31.24913, 31.24719, 31.24584, 31.24595, 31.245407, 31.24547, 31.246111, 31.24709, 31.24488, 31.246111, 31.22581, 31.21255, 31.20077, 31.20175, 31.20452, 31.20762, 31.20889, 31.20901, 31.21223, 31.427250, 31.404701, 31.39457, 31.38437, 31.37395, 31.36064, 31.34446, 31.34027, 31.33872, 31.33334, 31.32703, 31.32984, 31.31794, 31.300966, 31.283386, 31.27564, 31.26734, 31.25662, 31.24752, 31.23889, 31.23266, 31.22307, 31.2135);

        Collections.addAll(stations2, "المرج الجديدة","المرج","عزبة النخل","عين شمس", "مطرية", "حلمية الزيتون", "حدائق الزيتون", "سراي القبة", "حمامات القبة", "كوبري القبة", "منشية الصدر", "الدمرداش", "غمرة", "الشهداء", "احمد عرابي", "جمال عبد الناصر", "السادات", "سعد زغلول", "السيدة زينب", "الملك الصالح", "مار جرجس", "الزهراء", "دار السلام", "حدائق المعادي", "المعادي", "ثكانات المعادي", "طره البلد", "كوسيكا", "طره الاسمنت", "المعصرة", "حدائق حلوان", "وادي حوف", "جامعة حلوان", "عين حلوان", "حلوان", "شبرا الخيمة", "كلية الزاعة", "المظلات", "الخلفاوي", "سانت تريز", "روض الفرج", "المسرة", "العتبة"  ," محمد نجيب",  "اوبرا", "الدقي", "البحوث", "جامعة القاهرة", "فيصل ", "الجيزة", "ام المصريين", "ساقية مكي ", "المنيب", "عدلي منصور", "الهايكستيب ", "عمر ابن الخطاب", "قباء", "هشام بركات", "النزهة", "نادي الشمس", "الف المسكن ", "هيليوبلس", "هارون", "الاهرام", "كلية البنات ", "الاستاد", "ارض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية",  "ماسبيرو", "صفاء حجازي", "كيت كات");

        ArrayAdapter b = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stations2);
        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stations);
         starttext.setAdapter(a);
        endtext.setAdapter(b);
        Collections.addAll(line1, "المرج الجديدة","المرج","عزبة النخل","عين شمس", "مطرية", "حلمية الزيتون", "حدائق الزيتون", "سراي القبة", "حمامات القبة", "كوبري القبة", "منشية الصدر", "الدمرداش", "غمرة", "الشهداء", "احمد عرابي", "جمال عبد الناصر", "السادات", "سعد زغلول", "السيدة زينب", "الملك الصالح", "مار جرجس", "الزهراء", "دار السلام", "حدائق المعادي", "المعادي", "ثكانات المعادي", "طره البلد", "كوسيكا", "طره الاسمنت", "المعصرة", "حدائق حلوان", "وادي حوف", "جامعة حلوان","عين حلوان","حلوان");
        Collections.addAll(line2, "شبرا الخيمة", "كلية الزاعة", "المظلات", "الخلفاوي", "سانت تريز", "روض الفرج", "المسرة", "الشهداء", "العتبة", " محمد نجيب", "السادات", "اوبرا", "الدقي", "البحوث", "جامعة القاهرة", "فيصل ", "الجيزة", "ام المصريين", "ساقية مكي ", "المنيب");
        Collections.addAll(line3, "عدلي منصور", "الهايكستيب ", "عمر ابن الخطاب", "قباء", "هشام بركات", "النزهة", "نادي الشمس", "الف المسكن ", "هيليوبلس", "هارون", "الاهرام", "كلية البنات ", "الاستاد", "ارض المعارض", "العباسية", "عبده باشا", "الجيش", "باب الشعرية", "العتبة", "جمال عبد الناصر", "ماسبيرو", "صفاء حجازي","كيت كات");
//
      tts = new TextToSpeech(this, this);
        pref = getPreferences(MODE_PRIVATE);
        textp = getPreferences(MODE_PRIVATE);
//        sound = pref.getBoolean("sound", false);

//        if (sound) {
//            soundIcon.setImageResource(R.drawable.sound_on);
//
//        } else {
//            soundIcon.setImageResource(R.drawable.sound_off);
//        }
        String path_view;
        path_view = textp.getString("path_view", "");
        pathview.setText(path_view);
//        if (!sound) {
//            YoYo.with(Techniques.Tada).duration(1600).repeat(1).playOn(n);
//        }
        YoYo.with(Techniques.Tada).duration(1600).repeat(1).playOn(n);
    }

    @Override
    protected void onDestroy() {
//        tts.stop();
//        tts.shutdown();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        SharedPreferences.Editor editor = pref.edit();
//        editor.putBoolean("sound", sound);
        SharedPreferences.Editor editor1 = pref.edit();
        editor1.putString("path_view", pathview.getText().toString());
        super.onBackPressed();
    }

    public void sameline(List<String> line1, String start, String end, String b, String f) {


        if (line1.indexOf(end) - line1.indexOf(start) > 0) {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+f+"\n");
            int v = line1.indexOf(end) - line1.indexOf(start);
            pathview.append("" + line1.subList(line1.indexOf(start), line1.indexOf(end) + 1) + "\n");
            pathview.append( "  عدد المحطات= "+ v+  "\n");
            if (v <= 9) {
                pathview.append("سعر التذكرة =5 جنيهات " + "\n");
            } else if (v <= 16) {
                pathview.append("سعر التذكرة يساوي=7 حنيهات " + "\n");
            } else {
                pathview.append("سعر التذكرة يساوي =10جنيهات" + "\n");
            }
            pathview.append( "الوقت المستغرق في الرحلة="+(v*2)+"دقيقة"+"\n");
//            if (sound) {
//                String sk;
//                sk = "to reach " + end + "station" + "take in the same line " + v + "stations" + "in" + f + "direction";
////               tts.speak(sk, TextToSpeech.QUEUE_FLUSH, null, null);
//            }
        } else if (line1.indexOf(end) - line1.indexOf(start) < 0) {
            int v = line1.indexOf(start) - line1.indexOf(end);
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+b+"\n");
            Collections.reverse(line1);
            pathview.append("" + line1.subList(line1.indexOf(start), line1.indexOf(end) + 1) + "\n");
            Collections.reverse(line1);
            pathview.append("  = " + v + "\n");
            if (v >= 9) {
                pathview.append("سعر التذكرة =5 جنيهات " + "\n");
            } else if (v <= 16) {
                pathview.append("سعر التذكرة يساوي=7 حنيهات " + "\n");
            } else {
                pathview.append("سعر التذكرة يساوي =10جنيهات" + "\n");
            }
            pathview.append( "الوقت المستغرق في الرحلة="+(v*2)+"دقيقة"+"\n");

//            if (sound) {
//                String sk;
//                sk = "to reach " + end + "station" + "take in the same line " + v + "stations" + "in" + b + "direction";
//         //      tts.speak(sk, TextToSpeech.QUEUE_FLUSH, null, null);
//            }
        } else {
            pathview.append("انت بالفعل في هذه المحطة" + "\n");


        }
    }

    public void throughline(List<String> line1, String start, String end) {
        int c;
        c = line1.indexOf(end) - line1.indexOf(start);
        if (c > 0) {
            pathview.append("" + line1.subList(line1.indexOf(start), line1.indexOf(end) + 1) + "\n");
        }
        if (c < 0) {
            Collections.reverse(line1);
            pathview.append("" + line1.subList(line1.indexOf(start), line1.indexOf(end) + 1) + "\n");
            Collections.reverse(line1);
        }
        if (c == 0) {
            pathview.append("بالفعل انت في تلك المحطة  " + "\n");
        }
    }

    public void changeline(List<String> line1, String start, String end, String r, List<String> line2, String s1, String s2, String c1, String c2) {
        String sk1, sk2;
        if (line1.indexOf(start) > line1.indexOf(r)) {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+s1+"\n");
            int v1 = line1.indexOf(r) - line1.indexOf(start);
            v1 = Math.abs(v1);
            sk1 = "to reach" + end + "station" + "take this stations in " + s1 + "direction" + "and get out the train in" + r + "station" + "after" + v1 + "station";

        } else {
            int v1 = line1.indexOf(r) - line1.indexOf(start);
            v1 = Math.abs(v1);
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+s2+"\n");
            sk1 = "to reach" + end + "station" + "take this stations in " + s2 + "Direction" + "and get out the train in" + r + "station" + "after" + v1 + "station";

        }
        throughline(line1, start, r);
        pathview.append("الخروج من قطار المترو للتحويل في محطة "+r+"\n");
        if (line2.indexOf(end) > line2.indexOf(r)) {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+c2+"\n");
            int v2 = line2.indexOf(r) - line2.indexOf(end);
            v2 = Math.abs(v2);
            sk2 = "then change to " + c2 + "direction" + "and take" + v2 + "stations to arrive to" + end;


        } else {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+c1+"\n");
            int v2 = line2.indexOf(r) - line2.indexOf(end);
            v2 = Math.abs(v2);
            sk2 = "then change to " + c1 + "direction" + "and take" + v2 + "stations to arrive to" + end;

        }
        throughline(line2, r, end);
        int v = ((Math.abs(line1.indexOf(start) - line1.indexOf(r))) + (Math.abs((line2.indexOf(end) - line2.indexOf(r)))));

        pathview.append( "  عدد المحطات= " +v + "\n");
        pathview.append( "الوقت المستغرق في الرحلة="+(v*2)+"دقيقة"+"\n");
        if (v <= 9) {
            pathview.append("سعر التذكرة =5 جنيهات " + "\n");
        } else if (v <= 16) {
            pathview.append("سعر التذكرة يساوي=7 حنيهات " + "\n");
        } else {
            pathview.append("سعر التذكرة يساوي =10جنيهات" + "\n");
        }
        pathview.append("وقت التغيير بين الخطوط =5 دقائق" + "\n");
//        if (sound) {
////            tts.speak(sk1 + sk2, TextToSpeech.QUEUE_FLUSH, null, null);
//        }
    }

    public void changeline(List<String> line1, String start, String end, String r, List<String> line2, String s1, String s2, String c1, String c2, boolean c) {
        String sk1, sk2;
        if (line1.indexOf(start) > line1.indexOf(r)) {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+s1+"\n");
            int v1 = line1.indexOf(r) - line1.indexOf(start);
            v1 = Math.abs(v1);
            sk1 = "to reach" + end + "station" + "take this stations in " + s1 + "direction" + "and get out the train in" + r + "station" + "after" + v1 + "station";

        } else {
            int v1 = line1.indexOf(r) - line1.indexOf(start);
            v1 = Math.abs(v1);
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+s2+"\n");
            sk1 = "to reach" + end + "station" + "take this stations in " + s2 + "Direction" + "and get out the train in" + r + "station" + "after" + v1 + "station";

        }
        throughline(line1, start, r);
        pathview.append("الخروج من قطار المترو للتحويل في محطة "+r+"\n");
        if (line2.indexOf(end) > line2.indexOf(r)) {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+c2+"\n");
            int v2 = line2.indexOf(r) - line2.indexOf(end);
            v2 = Math.abs(v2);
            sk2 = "then change to " + c2 + "direction" + "and take" + v2 + "stations to arrive to" + end;


        } else {
            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه "+c1+"\n");
            int v2 = line2.indexOf(r) - line2.indexOf(end);
            v2 = Math.abs(v2);
            sk2 = "then change to " + c1 + "direction" + "and take" + v2 + "stations to arrive to" + end;

        }
        throughline(line2, r, end);
        int v = ((Math.abs(line1.indexOf(start) - line1.indexOf(r))) + (Math.abs((line2.indexOf(end) - line2.indexOf(r)))));
        v = v - 2;
        if (v <= 9) {
            pathview.append("سعر التذكرة =5 جنيهات " + "\n");
        } else if (v <= 16) {
            pathview.append("سعر التذكرة يساوي=7 حنيهات " + "\n");
        } else {
            pathview.append("سعر التذكرة يساوي =10جنيهات" + "\n");
        }
        v = v + 2;
        pathview.append( "  عدد المحطات= " +v + "\n");
        pathview.append( "الوقت المستغرق في الرحلة="+(v*2)+"دقيقة"+"\n");
        pathview.append("الوقت المستغرق في التحويل =5 دقائق" + "\n");
//        if (sound) {
////            tts.speak(sk1 + sk2, TextToSpeech.QUEUE_FLUSH, null, null);
//        }
    }

    public void path(View view) {
//        if (!sound) {
//            YoYo.with(Techniques.Tada).duration(500).repeat(4).playOn(soundIcon);
//        }

        pathview.setText(" ");

        String start = starttext.getText().toString();
        String end = endtext.getText().toString();

        boolean w1 = line1.contains(start) || (line2.contains(start)) || line3.contains(start);
        if (!w1) {
            pathview.append("محطة البداية غير موجودة بالمترو" + "\n");
            YoYo.with(Techniques.Tada).duration(500).repeat(2).playOn(starttext);
        }
        boolean w2 = (line1.contains(end) || line2.contains(end) || line3.contains(end));

        if (!w2) {
            pathview.append("محطة النهاية غير موجودة بالمترو " + "\n");
            YoYo.with(Techniques.Tada).duration(500).repeat(2).playOn(endtext);
        }
        if ((w1 && w2)) {

            if (line1.contains(end) && line1.contains(start)) {
                sameline(line1, start, end, "المرج", "حلوان");
                if ((start.equals("الشهداء") || start.equals("السادات")) && (end.equals("الشهداء") || end.equals("السادات"))) {
                    pathview.append("*--------------------*" + "\n" + "\n");
                    pathview.append("Another way " + "\n");
                    sameline(line2, start, end, "شبرا الخبمة", "المنيب");

                }
            } else if (line2.contains(end) && line2.contains(start)) {

                sameline(line2, start, end, "شبرا الخبمة", "المنيب");
            } else if (line3.contains(end) && line3.contains(start)) {

                sameline(line3, start, end, "عدلي منصور", "كيت كات");
            } else if (start.equals("جمال عبد الناصر") && line2.contains(end) && (line2.indexOf(end) < line2.indexOf("العتبة"))) {

                changeline(line3, start, end, "العتبة", line2, "عدلي منصور", "كيت كات", "المنيب", " شبرا");

            } else if ((line1.contains(start) && line2.contains(end)) || (line1.contains(end) && line2.contains(start))) {

                if (line1.contains(start)) {
                    if (start.equals("احمد عرابي")) {
                        if (line2.indexOf(end) < line2.indexOf("السادات")) {
                            changeline(line1, start, end, "الشهداء", line2, "المرج", "حلوان", "شبرا", "المنيب");
                        } else {
                            changeline(line1, start, end, "Sadat", line2, "المرج", "حلوان", "شبرا", "المنيب");
                        }
                    } else if (start.equals("جمال عبد الناصر")) {
                        if (line2.indexOf(end) > line2.indexOf("الشهداء")) {
                            changeline(line1, start, end, "السادات", line2, "المرج", "حلوان", "شبرا", "المنيب");
                        } else {
                            changeline(line1, start, end, "الشهداء", line2, "المرج", "حلوان", "شبرا", "المنيب");
                        }
                    } else {
                        if (((Math.abs(line1.indexOf(start) - line1.indexOf("الشهداء"))) < (Math.abs(line1.indexOf(start) - line1.indexOf("السادات"))))) {

                            changeline(line1, start, end, "الشهداء", line2, "المرج", "حلوان", "شبرا", "المنيب");
                        } else {
                            changeline(line1, start, end, "السادات", line2, "المرج", "حلوان", "شبرا", "المنيب");
                        }

                    }


                } else {

                    if (start.equals("العتبة")) {
                        if (line1.indexOf(end) < line1.indexOf("السادات")) {
                            changeline(line2, start, end, "الشهداء", line1, "شبرا", "المنيب", "المرج", "حلوان");
                        } else {
                            changeline(line2, start, end, "الشهداء", line1, "شبرا", "المنيب", "المرج", "حلوان");
                        }
                    } else if (start.equals("محمد نجيب")) {
                        if (line1.indexOf(end) > line1.indexOf("الشهداء")) {
                            changeline(line2, start, end, "السادات", line1, "شبرا", "المنيب", "المرج", "حلوان");
                        } else {
                            changeline(line2, start, end, "الشهداء", line1, "شبرا", "المنيب", "المرج", "حلوان");
                        }
                    } else {
                        if (((Math.abs(line2.indexOf(start) - line2.indexOf("الشهداء"))) < (Math.abs(line2.indexOf(start) - line2.indexOf("السادات"))))) {

                            changeline(line2, start, end, "الشهداء", line1, "شبرا", "المنيب", "المرج", "حلوان");
                        } else {

                            changeline(line2, start, end, "السادات", line1, "شبرا", "المنيب", "المرج", "حلوان");
                        }

                    }


                }
            } else if ((line3.contains(start) && line2.contains(end)) || (line3.contains(end) && line2.contains(start))) {
                if (line3.contains(start)) {
                    changeline(line3, start, end, "العتبة", line2, "عدلي منصور", "كيت كات", "شبرا", "المنيب");
                } else if (line2.contains(start)) {
                    changeline(line2, start, end, "العتبة", line3, "شبرا", "المنيب", "عدلي منصور", "كيت كات");
                }
            } else if ((line3.contains(start) && line1.contains(end)) || (line3.contains(end) && line1.contains(start))) {

                if (line1.contains(start)) {
                    if ((line1.indexOf(start) <= line1.indexOf("الشهداء"))) {

                        if (line3.indexOf(end) < line3.indexOf("العتبة")) {
                            if (s1.isChecked()) {
                                Toast.makeText(this, "This mode shows the easiest way, not the shortest", Toast.LENGTH_SHORT).show();

                                changeline(line1, start, end, "جمال عبد الناصر", line3, "المرج", "حلوان", "عدلي منصور", "كيت كات", true);

                            } else {
                                int v = 0;
                                int v1, v3;
                                String sk;
                                v1 = Math.abs(line1.indexOf(start) - line1.indexOf("الشهداء"));
                                v3 = Math.abs(line3.indexOf(end) - line3.indexOf("العتبة"));
                                sk = "take this stations in حلوان direction and get out of the train after" + v1 + "at Elshohdaa station then change to Elmonib direction and take one station to Elatabaa station and get out the train and change to عدلي منصور direction and take" + v3 + "station to arrive to " + end;
                                pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه حلوان"+"\n");
                                throughline(line1, start, "الشهداء");
                                pathview.append("الخروج من قطار المترو للتحويل في محطةالشهداء "+"\n");
                                v = Math.abs(line1.indexOf(start) - line1.indexOf("الشهداء"));
                                pathview.append("وبعد ذلك الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه شبرا"+"\n");
                                throughline(line2, "الشهداء", "العتبة");
                                pathview.append("الخروج من قطار المترو للتحويل في محطة العتبة"+"\n");
                                v = v + 1;
                                pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه عدلي منصور"+"\n");
                                throughline(line3, "العتبة", end);
                                v = v + Math.abs(line3.indexOf(end) - line3.indexOf("العتبة"));
                                pathview.append( "  عدد المحطات= " +v + "\n");
                                pathview.append( "الوقت المستغرق في الرحلة="+(v*2)+"دقيقة"+"\n");

                                if (v <= 9) {
                                    pathview.append("سعر التذكرة =5 جنيهات " + "\n");
                                } else if (v <= 16) {
                                    pathview.append("سعر التذكرة يساوي=7 حنيهات " + "\n");
                                } else {
                                    pathview.append("سعر التذكرة يساوي =10جنيهات" + "\n");
                                }

                                pathview.append("زمن التحويل عشر دقائق" + "\n");
//                                if (sound) {
////                                    tts.speak(sk, TextToSpeech.QUEUE_FLUSH, null, null);
//                                }
                            }
                        } else {
                            changeline(line1, start, end, "جمال عبد الناصر", line3, "المرج", "حلوان", "عدلي منصور", "كيت كات");



                        }
                    } else {
                        changeline(line1, start, end, "جمال عبد الناصر", line3, "المرج", "حلوان", "عدلي منصور", "كيت كات");

                    }
                } else {
                    if ((line3.indexOf(start) < line3.indexOf("العتبة")) && (line1.indexOf("جمال عبد الناصر") > line1.indexOf(end))) {
                        if (s1.isChecked()) {
                            Toast.makeText(this, "This mode shows the easiest way, not the shortest", Toast.LENGTH_SHORT).show();
                            changeline(line3, start, end, "جمال عبد الناصر", line1, "عدلي منصور", "كيت كات", "المرج", "حلوان",true);
                        } else {
                            int v = 0;
                            int v1 = line3.indexOf("العتبة") - line3.indexOf(start);
                            v1 = Math.abs(v1);
                            int v3;
                            v3 = line1.indexOf(end) - line1.indexOf("الشهداء");
                            v3 = Math.abs(v3);
                            String sk = "take this stations in كيت كات direction and get out the train after" + v1 + "stations at Elatabaa station then change To Shoubra direction and take one station to el shohdaa station ";
                            pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه الكيت كات"+"\n");
                            throughline(line3, start, "العتبة");
                            pathview.append("الخروج من قطار المترو للتحويل في محطةالعتبة  "+"\n");

                            v = (Math.abs(line3.indexOf(start) - line3.indexOf("العتبة"))) + 1;
                            pathview.append("وبعد ذلك الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه شبرا " + "\n");
                            throughline(line2, "العتبة", "الشهداء");
                            pathview.append("الخروج من قطار المترو للتحويل في محطةالشهداء  "+"\n");
                            v = v + Math.abs(line1.indexOf(end) - line1.indexOf("الشهداء"));
                            if (line1.indexOf(end) < line1.indexOf("الشهداء")) {
                                sk = sk + " then change to  المرج direction and take" + v3 + "station to arrive to " + end;
                                pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه المرج " + "\n");

                            } else {
                                sk = sk + " then change to  حلوان direction and take" + v3 + "station to arrive to " + end;
                                pathview.append("الركوب لقطار المترو والمرور علي تلك المحطات في اتجاه حلوان" +"\n");
                            }
                            throughline(line1, "الشهداء", end);
                            pathview.append( "  عدد المحطات =" +v + "\n");
                            pathview.append("الوقت المستغرق في الرحلة="+(v*2)+"دقيقة"+"\n");
//                            if (sound) {
////                                tts.speak(sk, TextToSpeech.QUEUE_FLUSH, null, null);
//                            }
                            if (v <= 9) {
                                pathview.append("سعر التذكرة =5 جنيهات " + "\n");
                            } else if (v <= 16) {
                                pathview.append("سعر التذكرة يساوي=7 حنيهات " + "\n");
                            } else {
                                pathview.append("سعر التذكرة يساوي =10جنيهات" + "\n");
                            }

                            pathview.append("الوقت المستغرق في التحويل بين الخطوط عشر دقائق " + "\n");
                        }
                    } else {

                        changeline(line3, start, end, "جمال عبد الناصر", line1, "عدلي منصور", "كيت كات", "المرج", "حلوان", true);

                    }

                }
            }

        }

    }

    public void ef(View view) {
        if (s1.isChecked()) {
            if (s1.isChecked()) {
                Toast.makeText(this, "save effort mode is on now", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "save effort mode is off now", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onInit(int i) {
//        tts.setSpeechRate(0.6f);
    }
    public void changesoundmode(View view) {
        Toast.makeText(this, "الشرح الصوتي غير متاح حاليا", Toast.LENGTH_SHORT).show();
//        if (!sound) {
//            Toast.makeText(this, "click on view path for voice description", Toast.LENGTH_SHORT).show();
//
//        }
//        if (sound) {
//            soundIcon.setImageResource(R.drawable.sound_off);
//            sound = false;
//        } else {
//            soundIcon.setImageResource(R.drawable.sound_on);
//            sound = true;
//        }
//        if (!sound) {
////            tts.stop();
//        }

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
    public String getshorteststationnormal(String place2) {
        try {

            int index = 0;
            Location loc2 = new Location("");
            List<Float> distances = new ArrayList<>();
            ArrayList<Location> stationslocations = new ArrayList<>();
            Geocoder geocoder = new Geocoder(this);

            List<Address> adress2list = geocoder.getFromLocationName(place2, 1);
            loc2.setLongitude(adress2list.get(0).getLongitude());
            loc2.setLatitude(adress2list.get(0).getLatitude());

            for (int i = 0; i < stations.size(); i++) {
                Location loc1 = new Location("");
                loc1.setLatitude(latstations.get(i));
                loc1.setLongitude(longstations.get(i));
                stationslocations.add(loc1);
            }
            for (int n = 0; n < stationslocations.size(); n++) {
                distances.add(stationslocations.get(n).distanceTo(loc2));
            }
            for (int m = 0; m < distances.size(); m++) {
                if (distances.get(m) == Collections.min(distances)) {
                    index = m;
                }
            }

            return stations.get(index);


        } catch (IOException e) {
            Toast.makeText(this, "مشكلة في اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);

        }
    }

    public String getshorteststation(String place2,int s,int f) {
        try {

            int index = 0;
            Location loc2 = new Location("");
            List<Float> distances = new ArrayList<>();
            ArrayList<Location> stationslocations = new ArrayList<>();
            Geocoder geocoder = new Geocoder(this);

            List<Address> adress2list = geocoder.getFromLocationName(place2, 1);
            loc2.setLongitude(adress2list.get(0).getLongitude());
            loc2.setLatitude(adress2list.get(0).getLatitude());

            for (int i = s; i < f; i++) {
                Location loc1 = new Location("");
                loc1.setLatitude(latstations.get(i));
                loc1.setLongitude(longstations.get(i));
                stationslocations.add(loc1);
            }
            for (int n = 0; n < stationslocations.size(); n++) {
                distances.add(stationslocations.get(n).distanceTo(loc2));
            }

            float mindis=Collections.min(distances);
            if (mindis<1000) {
                for (int m = 0; m < distances.size(); m++) {
                    if (distances.get(m) == mindis) {
                        index = m+s;
                    }
                }


                    return stations.get(index);


            }
            else {
                return getshorteststationnormal(place2);

            }



        } catch (IOException e) {
            Toast.makeText(this, "مشكلة في اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);

        }
    }
    public String getshorteststation(String place2) {
        if (s1.isChecked()) {
            if (!starttext.getText().toString().equals("")){
                if(line1.contains(starttext.getText().toString()))
                {return getshorteststation(disttext.getText().toString(),0,line1.size());}
                else if(line2.contains(starttext.getText().toString()))
                {return getshorteststation(disttext.getText().toString(),line1.size(),line1.size()+line2.size());}
                else if(line3.contains(starttext.getText().toString()))
                {
                    return getshorteststation(disttext.getText().toString(),line1.size()+line2.size(),line1.size()+line2.size()+line3.size());

                }
                else {
                    Toast.makeText(this, "the start station not exist please check the map of metro at the bottom to be sure about name", Toast.LENGTH_SHORT).show();
                    return "";

                }
            }
            else{
                Toast.makeText(this, "في وضع توفير المجهود حدد محطة البداية اولا", Toast.LENGTH_SHORT).show();
                return "";
            }
        } else {
            try {

                int index = 0;
                Location loc2 = new Location("");
                List<Float> distances = new ArrayList<>();
                ArrayList<Location> stationslocations = new ArrayList<>();
                Geocoder geocoder = new Geocoder(this);

                List<Address> adress2list = geocoder.getFromLocationName(place2, 1);
                loc2.setLongitude(adress2list.get(0).getLongitude());
                loc2.setLatitude(adress2list.get(0).getLatitude());

                for (int i = 0; i < stations.size(); i++) {
                    Location loc1 = new Location("");
                    loc1.setLatitude(latstations.get(i));
                    loc1.setLongitude(longstations.get(i));
                    stationslocations.add(loc1);
                }
                for (int n = 0; n < stationslocations.size(); n++) {
                    distances.add(stationslocations.get(n).distanceTo(loc2));
                }
                for (int m = 0; m < distances.size(); m++) {
                    if (distances.get(m) == Collections.min(distances)) {
                        index = m;
                    }
                }

                return stations.get(index);


            } catch (IOException e) {
                Toast.makeText(this, "مشكلة في اتصاك بالانترنت ", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);

            }
        }
    }




    public String getshorteststation(Location loc2){

            int index = 0;
            List<Float> distances = new ArrayList<>();
            ArrayList<Location> stationslocations = new ArrayList<>();
            Geocoder geocoder = new Geocoder(this);


            for (int i = 0; i < stations.size(); i++) {
                Location loc1 = new Location("");
                loc1.setLatitude(latstations.get(i));
                loc1.setLongitude(longstations.get(i));
                stationslocations.add(loc1);

            }
            for (int n = 0; n < stationslocations.size(); n++) {
                distances.add(stationslocations.get(n).distanceTo(loc2));
            }

            for (int m = 0; m < distances.size(); m++) {
                if (distances.get(m) == Collections.min(distances)) {
                    index = m;
                }
            }

            return stations.get(index);

        }


    public void getstartlocationonmap(View view) {
        if(!starttext.getText().toString().equals("")) {
            Intent a = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + starttext.getText().toString()));
              startActivity(a);
        }

    }


    @Override
    public void onFailure(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
        Toast.makeText(this, "حدث خطئ في اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@NonNull ArrayList<Location> arrayList) {
       double lat = arrayList.get(0).getLatitude();
       double lon = arrayList.get(0).getLongitude();
        current = new Location("");
       current.setLatitude(lat);
       current.setLongitude(lon);
       starttext.setText(getshorteststation(current));

     //   Geocoder g=new Geocoder(this);
//        try {
//            List <Address> a=g.getFromLocation(arrayList.get(0).getLatitude(),arrayList.get(0).getLongitude(),1);
//            pathview.setText(a.get(0).getAddressLine(0));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//

   }

    public void getfinaldistnation(View view) throws IllegalAccessException, InstantiationException {

        if (disttext.getText().toString().equals("")) {
            Toast.makeText(this, "ادخل عنوان", Toast.LENGTH_LONG).show();

        } else {

            Toast.makeText(this, "لعدم التعرض للحصول علي محطة خاطئة تاكد من عنوانك علي الخريطة", Toast.LENGTH_LONG).show();
            tts.speak("لعدم التعرض للحصول علي محطة خاطئة تاكد من عنوانك علي الخريطة", TextToSpeech.QUEUE_FLUSH, null, null);
            YoYo.with(Techniques.Tada).duration(1000).repeat(5).playOn(mapon);
            endtext.setText(getshorteststation(disttext.getText().toString() + "Egypt"));









        }



    }
    public void viewmap(View view) {

        Intent a= new Intent(this,mapview.class);
        startActivity(a);

    }
    public void views(View view) {

        Intent a= new Intent(this,MainActivity3.class);
        startActivity(a);

    }
    public void neareststation(View view) {
        airLocation = new AirLocation(this, this, true, 0, "");
        airLocation.start();
    }


    public void toat(View view) {
        Intent a= new Intent(this,mapview.class);
        startActivity(a);

    }

    public void viewadressonmap(View view) {
        if(!disttext.getText().toString().equals("")) {
            Intent a = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + disttext.getText().toString()));
            startActivity(a);
        }
    }
}



