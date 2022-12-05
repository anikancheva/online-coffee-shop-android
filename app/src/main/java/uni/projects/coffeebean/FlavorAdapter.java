package uni.projects.coffeebean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class FlavorAdapter extends RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder> {

    private final List<Flavor> flavors;

    public static List<String> CHECKED_FLAVORS;

    public FlavorAdapter(List<Flavor> flavors){
        this.flavors=flavors;
        CHECKED_FLAVORS=new ArrayList<>();
    }

    @NonNull
    @Override
    public FlavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.flavor_item, parent, false);
        return new  FlavorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlavorViewHolder holder, int position) {

       TextView flavorName= holder.itemView.findViewById(R.id.txtName);
       flavorName.setText(flavors.get(position).getName());

        CheckBox cbFlavor=holder.itemView.findViewById(R.id.cbFlavor);
        cbFlavor.setOnCheckedChangeListener((compoundButton, b) -> {
            if(compoundButton.isChecked()){
                CHECKED_FLAVORS.add(flavorName.getText().toString());
            }else {
                CHECKED_FLAVORS.remove(flavorName.getText().toString());
            }
        });

    }

    @Override
    public int getItemCount() {
        return flavors.size();
    }

    class FlavorViewHolder extends RecyclerView.ViewHolder {

        public FlavorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
