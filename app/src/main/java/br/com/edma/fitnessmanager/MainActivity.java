package br.com.edma.fitnessmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.ReceiverCallNotAllowedException;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<MainItem> mainItens = new ArrayList<>();
        mainItens.add(new MainItem(1 , R.drawable.ic_baseline_fitness_center_24, R.string.label_imc, Color.GREEN));
        mainItens.add(new MainItem(2 , R.drawable.ic_baseline_remove_red_eye_24, R.string.label_tmb, Color.YELLOW));

        rvMain = findViewById(R.id.main_rv);
        rvMain.setLayoutManager(new LinearLayoutManager(this));
        MainAdapter adapter = new MainAdapter(mainItens);
        adapter.setListener(id -> {
            switch (id){
                case 1:
                    startActivity(new Intent(MainActivity.this, ImcActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, TmbActivity.class));
                    break;
            }

        });
        rvMain.setAdapter(adapter);


    }

   private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder>{

        private List<MainItem> mainItems;
        private OnItemClickListener listener;

        public MainAdapter(List<MainItem> mainItems){
            this.mainItems = mainItems;
        }

        public void setListener(OnItemClickListener listener){
            this.listener = listener;
        }



       @NonNull
        @Override
        public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MainViewHolder(getLayoutInflater().inflate(R.layout.main_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
            MainItem mainItemCurrent = mainItems.get(position);
            holder.bind(mainItemCurrent);

        }

        @Override
        public int getItemCount() {
            return mainItems.size();
        }

       private   class MainViewHolder extends RecyclerView.ViewHolder{

           public MainViewHolder(@NonNull View itemView) {
               super(itemView);
           }

           public void bind(MainItem item){
               TextView txtName = itemView.findViewById(R.id.item_txt_name);
               ImageView imgIcon = itemView.findViewById(R.id.item_img_icon);
               LinearLayout btn_imc = (LinearLayout) itemView.findViewById(R.id.btn_imc);

               btn_imc.setOnClickListener(v -> {
                   listener.onClick(item.getId());
                   //startActivity (new Intent(MainActivity.this, ImcActivity.class));
               });

               txtName.setText(item.getTextStringId());
               imgIcon.setImageResource(item.getDrawableId());
               btn_imc.setBackgroundColor(item.getColor());
           }
       }
    }


}




