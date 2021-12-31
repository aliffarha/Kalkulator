package com.example.kalkulator;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;




import java.util.ArrayList;
import java.util.Map;

public class mykalkulator extends AppCompatActivity {


    EditText inputangka1, inputangka2;
    TextView hasilhitung;



    RadioGroup radioGroup;
    RadioButton rb;
    int pilihtombol, temp=1, id = 1;
    TextView txtjudul;
    SharedPreferences pref;
    private ArrayList<Resultdata> listdata;
    private RecyclerView listperhitungan;

    ResultdataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mykalkulator);
        initData();
        showArray();
        if (listdata.size() == 0){
            id = 1;
        }else {
            id = Integer.parseInt(listdata.get(listdata.size()-1).getId()+1);
        }



    }

     void initData() {

        inputangka1 =  findViewById(R.id.inputangka1);
        inputangka2 =  findViewById(R.id.inputangka2);
        hasilhitung =  findViewById(R.id.hasilhitung);


        radioGroup =  findViewById(R.id.radioGroup);
        listperhitungan = findViewById(R.id.listperhitungan);

        pref = this.getSharedPreferences(getString(R.string.shared_key), Context.MODE_PRIVATE);
        listdata = new ArrayList<>();
        temp = pref.getAll().size() + 1;
        txtjudul = findViewById(R.id.txtjudul);

        adapter = new ResultdataAdapter(listdata, this, pref);
        txtjudul.setText("Halo " + pref.getString(getString(R.string.username_key), "-"));


    }

    public void showArray() {
        Map<String, ?> allEntries = pref.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            getArray(entry.getKey(),entry.getValue().toString());
        }
    }

    public void cek() {
        pilihtombol = radioGroup.getCheckedRadioButtonId();
        rb = findViewById(pilihtombol);
        Toast.makeText(this, rb.getText(), Toast.LENGTH_SHORT).show();
    }

    public void saveToShared(String id, String hasil) {
        try {
            pref.edit().putString(id, hasil).apply();
            String value = pref.getString(id, "");
            getArray(id, value);
            temp++;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getArray(String no, String rwyt) {
        try {
            listperhitungan.setAdapter(new ResultdataAdapter(listdata, this, pref));
            listperhitungan.setLayoutManager(new LinearLayoutManager(this));
            listdata.add(new Resultdata(no, String.valueOf(rwyt)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("gagal tambah array");
        }
    }

        public void hasil(View v){
            try {
                try{
                    String idresult = String.valueOf(id);
                    double a1 = Double.parseDouble(inputangka1.getText().toString());
                    double a2 = Double.parseDouble(inputangka2.getText().toString());
                    double hasil;
                    cek();
                    if (rb.getText().equals("+")){
                        hasil = a1 + a2;
                        hasilhitung.setText("hasil : "+hasil);
                        String result = Double.toString(a1)+" + "+Double.toString(a2)+" = "+Double.toString(hasil);
                        saveToShared(idresult, result);
                    }else if (rb.getText().equals("-")){
                        hasil = a1 - a2;
                        hasilhitung.setText("hasil : "+hasil);
                        String result = Double.toString(a1)+" - "+Double.toString(a2)+" = "+Double.toString(hasil);
                        saveToShared(idresult, result);
                    }else if (rb.getText().equals("*")){
                        hasil = a1 * a2;
                        hasilhitung.setText("hasil : "+hasil);
                        String result = Double.toString(a1)+" x "+Double.toString(a2)+" = "+Double.toString(hasil);
                        saveToShared(idresult, result);
                    }else if (rb.getText().equals("/")){
                        hasil = a1 / a2;
                        hasilhitung.setText("hasil : "+hasil);
                        String result = Double.toString(a1)+" / "+Double.toString(a2)+" = "+Double.toString(hasil);
                        saveToShared(idresult, result);
                    }
                    id++;
                }catch (Exception e){
                    e.printStackTrace();
                    hasilhitung.setText("gagal");
                    Toast.makeText(this, "gagal", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                e.printStackTrace();
                Toast.makeText(this, "faill", Toast.LENGTH_SHORT).show();
            }
        }




public void logOut (View v){
        pref.edit().clear().apply();
    Intent it = new Intent(this, MainActivity.class);
    it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(it);
}



}