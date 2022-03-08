package tw.com.feast_test0301.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tw.com.feast_test0301.R;
import tw.com.feast_test0301.utils.Item;


//繼承RecyclerView.Adapter 泛型繼承自己本身(ItemAdapter)的內部類別(ItemViewHolder 下方的那個)
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    //JavaBean的List
    List<Item> items;


    //ItemAdapter的建構子
    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_page,parent,false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.getItemData(items.get(position),position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //內部類別，繼承RecyclerView.ViewHolder
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitle;
        private ImageView imageView;

        //內部類別的建構子，取得傳入View的內部物件。
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.titleTextViewID);
            imageView = itemView.findViewById(R.id.pageImageID);
        }

        //自製的方法，將傳入的JavaBean裡面內容，填入對應的物件內
        public void getItemData(Item itemData,int position){
            textTitle.setText(itemData.getTitle());
            imageView.setImageResource(itemData.getImage());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println(position);
                }
            });
        }
    }
}