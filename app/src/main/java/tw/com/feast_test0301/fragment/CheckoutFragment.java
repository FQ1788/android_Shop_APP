package tw.com.feast_test0301.fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.List;

import tw.com.feast_test0301.MainActivity;
import tw.com.feast_test0301.R;
import tw.com.feast_test0301.tool.urlPostTool;
import tw.com.feast_test0301.utils.OrderBean;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckoutFragment} factory method to
 * create an instance of this fragment.
 */
public class CheckoutFragment extends Fragment {
    private MainActivity mainActivity;

    public CheckoutFragment() {
    }
    public CheckoutFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);


        LinearLayout comOneLayout = view.findViewById(R.id.comOneLayoutID);
        TextView totalNumber = view.findViewById(R.id.totalNumberID);
        List<OrderBean> loadData =  mainActivity.getOrderListData();
        int totleMoney = 0;
        int OrderBeanNumber=0;
        for(OrderBean one : loadData){
            totleMoney += one.getComTotal();
            ConstraintLayout comStyle = (ConstraintLayout) getLayoutInflater().inflate(R.layout.order_data_style, null);
            TextView ordetTitle = comStyle.findViewById(R.id.ordetTitleID);
            TextView orderContext = comStyle.findViewById(R.id.orderContextID);
            ImageView orderImageView = comStyle.findViewById(R.id.orderImageViewID);
            Button removeButtonID =comStyle.findViewById(R.id.removeButtonID);


            orderImageView.setImageDrawable(one.getComImage());
            ordetTitle.setText(one.getComName());
            orderContext.setText("$"+one.getComTotal());
            int finalOrderBeanNumber = OrderBeanNumber;
            removeButtonID.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mainActivity.removeComData(finalOrderBeanNumber);
                    mainActivity.switchFragment(new CheckoutFragment(mainActivity));
                }
            });
            OrderBeanNumber++;
            comOneLayout.addView(comStyle);
        }
        totalNumber.setText("$"+totleMoney);


        Button payButtonID = view.findViewById(R.id.payButtonID);
        payButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(loadData);
                JSONArray orderJsonArrayData = null;
                try {
                    orderJsonArrayData = new JSONArray(loadData.toString());
                    System.out.println(orderJsonArrayData);
                    HashMap<String, JSONArray> orderHashData = new HashMap<String, JSONArray>();
                    String URL = "http://10.0.2.2:8080/ProjectDemo/DemoTest?state=addOrder";
                    orderHashData.put("jsonArrayData",orderJsonArrayData);
                    //傳送訂單資料
                    urlPostTool urlPostTool = new urlPostTool(URL,orderHashData);
                    urlPostTool.OnServerAnswerListener = new urlPostTool.NetEventListener() {
                        @Override
                        public void linkStart() {
                            System.out.println("傳送開始");
                        }

                        @Override
                        public void linkEnd(byte[] dataByte) {
                            System.out.println(new String(dataByte));
                            System.out.println("傳送結束");
                            mainActivity.removeList();
                            mainActivity.getData();
                        }
                    };
                    urlPostTool.start();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}