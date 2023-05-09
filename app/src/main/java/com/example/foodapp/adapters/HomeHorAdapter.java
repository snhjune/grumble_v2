package com.example.foodapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.R;
import com.example.foodapp.models.HomeHorModel;
import com.example.foodapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {
    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    /**
     *
     * @param updateVerticalRec
     * @param activity
     * @param list
     */
    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item,parent,false));
    }

    /**
     * Hardcoded restaurants stored in ArrayList.
     * Categorize the restuarants by its food types. (Ex. sushi,hamburger,pizza, and etc)
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        int currentPosition = holder.getAdapterPosition();
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel(R.drawable.sushirestaurant4,"Sushi West", "11:30 - 22:00", "4.2","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizzares2,"Catalano's Pizza", "10:30 - 20:00", "4.8","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.curry1,"Flavor of Punjab", "11:30 - 21:00", "4.7","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.burger4,"Long Beach Burger Bar", "11:30 - 22:00", "4.9","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.chinese3,"Wa Wa Restaurant", "11:30 - 21:00", "4.2","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.taco5,"Los Compadres Restauran", "11:30 - 21:00", "4.2","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.vege5,"Appu's Cafe", "11:30 - 21:00", "4.2","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.korean4,"Jun Jac Gu Ry Korean BBQ", "12:30 - 21:30", "4.9","Open Hours"));
            homeVerModels.add(new HomeVerModel(R.drawable.korean5,"Moonbowls", "11:30 - 21:00", "4.2","Open Hours"));

            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();

                    if(position == 0){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.sushirestaurant1,"Sushinaloa", "9:00 - 21:00", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.sushirestaurant2,"UENO SUSHI & ASIAN IZAKAYA", "11:30 - 21:30", "4.6","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.sushirestaurant3,"Kinokawa Japanese Restaurant", "10:30 - 20:00", "4.6","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.sushirestaurant4,"Sushi West", "11:30 - 22:00", "4.2","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.sushirestaurant5,"Sushi Ai - Long Beach", "10:30 - 21:00", "4.1","Open Hours"));
                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 1){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.pizzares1,"AC Bar and Lounge", "9:00 - 21:00", "4.9","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.pizzares2,"Catalano's Pizza", "10:30 - 20:00", "4.8","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.pizzares3,"Marri’s Pizza & Pasta Restaurant", "10:30 - 20:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.pizzares4,"Broadway Pizza & Grill", "10:30 - 20:00", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.pizzares5,"Thunderbolt Pizza", "10:30 - 20:00", "4.6","Open Hours"));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 2){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.burger1,"Dog Haus", "11:00 - 21:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.burger2,"Lazy Dog Restaurant & Bar", "10:30 - 22:00", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.burger3,"Dave's Burgers", "10:00 - 22:00", "4.2","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.burger4,"Long Beach Burger Bar", "11:30 - 22:00", "4.9","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.burger5,"Hamburger Mary's", "10:30 - 21:00", "4.8","Open Hours"));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 3){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.curry1,"Flavor of Punjab", "11:30 - 21:00", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.curry2,"Kamal Palace", "10:30 - 22:00", "4.8","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.curry3,"Natraj Cuisine of India", "11:00 - 21:30", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.curry4,"Flamin Curry", "10:00 - 21:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.curry5,"Appu's Cafe", "10:00 - 21:00", "4.5","Open Hours"));
                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 4){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.chinese1,"Chee Chinese Restaurant", "10:00 - 21:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.chinese2,"Yang Chow 2.0", "10:30 - 22:00", "4.6","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.chinese3,"Panda Garden", "11:30 - 22:30", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.chinese4,"Golden Chinese Express", "12:30 - 21:30", "4.9","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.chinese5,"Wa Wa Restaurant", "11:30 - 21:00", "4.2","Open Hours"));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 5){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.korean1,"Surawon All You Can Eat BBQ", "10:00 - 21:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.korean2,"MARINATE Korean BBQ", "10:30 - 22:00", "4.6","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.korean3,"Gangnam Korean BBQ", "11:30 - 22:30", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.korean4,"Jun Jac Gu Ry Korean BBQ", "12:30 - 21:30", "4.9","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.korean5,"Moonbowls", "11:30 - 21:00", "4.2","Open Hours"));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 6){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.vege1,"Vegan Castle", "10:00 - 21:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.vege2,"Higher Taste, Plant-Based", "10:30 - 22:00", "4.6","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.vege3,"Hart N Soul Vegan Cafe", "11:30 - 22:30", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.vege4,"Seabirds Kitchen", "12:30 - 21:30", "4.9","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.vege5,"Appu's Cafe", "11:30 - 21:00", "4.2","Open Hours"));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position == 7){
                        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                        homeVerModels.add(new HomeVerModel(R.drawable.taco1,"El Torito", "10:00 - 21:00", "4.5","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.taco2,"El Mercado Modern Cuisine", "10:30 - 22:00", "4.6","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.taco3,"Long Beach Taco Co.", "11:30 - 22:30", "4.7","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.taco4,"La Esquinita Mexican Grill", "12:30 - 21:30", "4.9","Open Hours"));
                        homeVerModels.add(new HomeVerModel(R.drawable.taco5,"Los Compadres Restauran", "11:30 - 21:00", "4.2","Open Hours"));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                }
            });
            if(select){
                if(position == 0){
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                }
            }

            else {
                if (row_index == position) {
                    holder.cardView.setBackgroundResource(R.drawable.change_bg);
                } else {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }

        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView );
        }
    }
}
