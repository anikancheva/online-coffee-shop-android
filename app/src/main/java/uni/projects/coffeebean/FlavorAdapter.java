package uni.projects.coffeebean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class FlavorAdapter extends RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder> {

    private final List<Flavor> flavors;

    public FlavorAdapter(List<Flavor> flavors){
        this.flavors=flavors;
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
