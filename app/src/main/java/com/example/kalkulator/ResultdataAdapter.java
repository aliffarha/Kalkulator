package com.example.kalkulator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultdataAdapter extends RecyclerView.Adapter<ResultdataAdapter.ViewHolder> {

    private ArrayList<Resultdata> listdata;
    private Context context;
    private SharedPreferences sharedPreferences;

    public ResultdataAdapter(ArrayList<Resultdata> listdata, Context context, SharedPreferences sharedPreferences) {
        this.listdata= listdata;
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        ViewHolder holder = new ViewHolder(inflater.inflate(R.layout.listdata, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Resultdata result = listdata.get(position);
        holder.result.setText(result.getResult());
    }

    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView result;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            result = itemView.findViewById(R.id.txresult);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int p = getLayoutPosition();
                    System.out.println("PANJANG KLIKK "+p);

                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    alert.setTitle("Hapus Data")
                            .setMessage("Ingin hapus data ?")
                            .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.cancel();
                                }
                            })
                            .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    String id = listdata.get(p).getId();

//                                    Map<String, ?> entries = sharedPreferences.getAll();
//                                    for (Map.Entry<String, ?> entry: entries.entrySet()) {
//                                        if (listdata.get(p).toString().equals(entry.getValue().toString())){
//                                            System.out.println(entry.getValue().toString());
//                                            sharedPreferences.edit().remove(entry.getKey()).commit();
//                                            break;
//                                        }
//                                    }
                                    sharedPreferences.edit().remove(id).commit();

                                    for (int j = 0; j < listdata.size(); j++) {
                                        if (id.equalsIgnoreCase(listdata.get(j).getId())){
                                            listdata.remove(j);
                                            notifyItemRemoved(j);
                                            notifyItemRangeChanged(j, listdata.size());
                                        }
                                    }
                                }
                            });
                    AlertDialog dialog = alert.create();
                    dialog.show();

                    return true;
                }
            });
        }
    }
}
