package com.example.kalkulator;

import androidx.annotation.NonNull;

public class Resultdata {
    private String result;
    private String id;


    public Resultdata(String id,String result){
        this.id = id;
        this.result = result;
    }

    public String getResult() { return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getId() { return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return result;
    }
}
