package tw.com.feast_test0301.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;


import tw.com.feast_test0301.R;
import tw.com.feast_test0301.utils.OrderHistory;


/**
 * 顯示歷史紀錄的Class
 */
public class OrderHistoryAdapter {

    public void showOrderHistory(View view){
        LinearLayout orderLayout = view.findViewById(R.id.orderHistoryLayoutID);
        List<OrderHistory> orderData= getData();
        for (OrderHistory oneData:orderData){
            ConstraintLayout comStyle = (ConstraintLayout) LayoutInflater.from(view.getContext()).inflate(R.layout.order_data_style,null);
            comStyle.setPadding(0,10,0,10);
            orderLayout.addView(comStyle);
            ImageView orderImage = comStyle.findViewById(R.id.orderImageViewID);
            TextView orderTitle =comStyle.findViewById(R.id.ordetTitleID);
            TextView orderContext = comStyle.findViewById(R.id.orderContextID);
            orderImage.setImageResource(oneData.getStoreImage());
            orderTitle.setText(oneData.getStoreName());
            orderContext.setText(oneData.getOrderData());
        }
    }

    public List<OrderHistory> getData(){
        List<OrderHistory> listOrderHistory =new ArrayList<OrderHistory>();
        listOrderHistory.add(new OrderHistory("麥當勞","一份餐點 * $179 \n 1月20日 訂單完成",R.drawable.logo1));
        listOrderHistory.add(new OrderHistory("小食代","一份餐點 * $250 \n 12月5日 訂單完成",R.drawable.logo02));
        listOrderHistory.add(new OrderHistory("食在健康","一份餐點 * $149 \n 2月7日 訂單完成",R.drawable.logo03));
        return listOrderHistory;
    }
}
