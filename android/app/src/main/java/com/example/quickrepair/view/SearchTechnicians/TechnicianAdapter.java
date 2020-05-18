package com.example.quickrepair.view.SearchTechnicians;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.quickrepair.R;

import java.util.ArrayList;
import java.util.List;

public class TechnicianAdapter extends BaseAdapter {

    List<Integer> technicianIds;
    List<String> technicianNames;
    List<Double> averageRatings;
    List<Double> prices;
    Context context;
    public TechnicianAdapter(Context context){
        this.context = context;
        technicianIds = new ArrayList<>();
        technicianNames = new ArrayList<>();
        averageRatings = new ArrayList<>();
        prices = new ArrayList<>();
    }
    public void setSource(List<Integer> technicianIds, List<String> technicianNames, List<Double> averageRatings, List<Double> prices){
        this.technicianIds = technicianIds;
        this.technicianNames = technicianNames;
        this.averageRatings = averageRatings;
        this.prices = prices;
    }

    @Override
    public int getCount() {
        System.out.println("Get count called size : " + technicianIds.size() );
        return technicianIds.size();
    }

    @Override
    public Object getItem(int position) {
        return technicianNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return technicianIds.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("Get view at pos" + position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View result = inflater.inflate( R.layout.search_technicians_text_list_item, parent , false);
        //Todo add buttons to navigate to request repair
        TextView numberText =  result.findViewById(R.id.number);
        System.out.println(numberText);
        TextView nameText =  result.findViewById(R.id.name);
        System.out.println(nameText);
        TextView priceText =  result.findViewById(R.id.price);
        System.out.println(priceText);
        TextView averageRatingText =  result.findViewById(R.id.average_rating);
        System.out.println(averageRatingText);
        //Todo set number to position

        numberText.setText(technicianIds.get(position));
        nameText.setText(technicianNames.get(position));
        priceText.setText("" +prices.get(position));
        averageRatingText.setText("" +averageRatings.get(position));
        return  result;
    }
}
