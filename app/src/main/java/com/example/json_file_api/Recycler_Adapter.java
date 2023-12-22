package com.example.json_file_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.UserHolder> {
    Get_JSON_MZ getJsonMz;
    List<JSON_USER_ITEM_MZ> userLists = new ArrayList<>();

    public Recycler_Adapter(Get_JSON_MZ getJsonMz) {
        this.getJsonMz = getJsonMz;
    }

    void setList(List<JSON_USER_ITEM_MZ> userLists) {
        this.userLists.addAll(userLists);
        this.notifyDataSetChanged();
    }

    public void clearList() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new UserHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_shower, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.textView_Name.setText(userLists.get(position).name);
        holder.textView_UserName.setText(userLists.get(position).username);
        holder.textView_Family.setText(userLists.get(position).email);
        AddressJava addressJava = (userLists.get(position).address);
        String fullAddress = "City: " + addressJava.city + "\nStreet: " + addressJava.street + "\nZipCode: " + addressJava.zipcode + "\nSuite: " + addressJava.suite + "\nGeo: " + addressJava.geo;
        holder.textView_ShowAddress.setText(fullAddress);
        CompanyJava company = userLists.get(position).company;
        String fullDetail = "Name: " + company.name + "\n bs: " + company.bs + "Catch Phrase: " + company.catchPhrase;
        holder.textView_Company.setText(fullDetail);

        int intValue = userLists.get(position).id;
        String stringTypeCast = String.valueOf(intValue);

        holder.textView_id.setText(stringTypeCast);

        String website = userLists.get(position).website;
        holder.textRecy_WebSite.setText(website);

        holder.linerLayout_adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                userLists.remove(position);
                notifyItemChanged(position);
                Toast.makeText(getJsonMz, "Data has been deleted", Toast.LENGTH_SHORT).show();
                return false;
            }
        });




    }


    @Override
    public int getItemCount() {
        return userLists.size();
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView textView_Name, textView_Family, textView_Company, textView_id, textView_UserName, textView_ShowAddress, textRecy_WebSite;
        LinearLayout linerLayout_adapter;
        public UserHolder(@NonNull View itemView) {
            super(itemView);

            textView_Name = itemView.findViewById(R.id.textRecy_Name);
            textView_Family = itemView.findViewById(R.id.textRecy_Family);
            textView_Company = itemView.findViewById(R.id.textRecy_Company);
            textView_id = itemView.findViewById(R.id.textRecy_ID);
            textView_UserName = itemView.findViewById(R.id.textRecy_Username);
            textView_ShowAddress = itemView.findViewById(R.id.textRecy_ShowAddress);
            textRecy_WebSite = itemView.findViewById(R.id.textRecy_WebSite);
            linerLayout_adapter = itemView.findViewById(R.id.linerLayout_adapter);
        }
    }
}
