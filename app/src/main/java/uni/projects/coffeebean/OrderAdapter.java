package uni.projects.coffeebean;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private final List<Map<String, Object>> orders;

    public OrderAdapter(List<Map<String, Object>> orders){
        this.orders=orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        TextView tvOrderDaata = holder.itemView.findViewById(R.id.orderData);
        Map<String, Object> currentOrder = orders.get(position);
        StringBuilder tvBuilder=new StringBuilder();
        currentOrder.values().forEach(v->{
            tvBuilder.append(v).append(" / ");
        });
        tvBuilder.delete(tvBuilder.length()-3, tvBuilder.length());
        tvOrderDaata.setText(tvBuilder);

        holder.itemView.findViewById(R.id.orderItem).setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class OrderViewHolder extends RecyclerView.ViewHolder {
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
