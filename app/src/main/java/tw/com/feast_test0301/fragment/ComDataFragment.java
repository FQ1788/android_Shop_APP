package tw.com.feast_test0301.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tw.com.feast_test0301.MainActivity;
import tw.com.feast_test0301.R;
import tw.com.feast_test0301.utils.DataBean;
import tw.com.feast_test0301.utils.OrderBean;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ComDataFragment#} factory method to
 * create an instance of this fragment.
 */
public class ComDataFragment extends Fragment {

    DataBean dataBean;
    int i = 0;
    MainActivity mainActivity;

    public ComDataFragment() {
        // Required empty public constructor
    }

    public ComDataFragment(DataBean dataBean,MainActivity mainActivity) {
        // Required empty public constructor
        this.dataBean = dataBean;
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_com_data, container, false);
        TextView comName = view.findViewById(R.id.comNameID);
        TextView comPrice = view.findViewById(R.id.comPriceID);
        TextView comNumber = view.findViewById(R.id.comNumberID);
        NumberPicker comNumberPick = view.findViewById(R.id.NumberPickerID);
        TextView comTotal = view.findViewById(R.id.comTotalID);
        ImageView comImage = view.findViewById(R.id.comImageID);

        int iPrice = Integer.parseInt(dataBean.getsComPrice());
        int iNumber = Integer.parseInt(dataBean.getsComNumber());
        comImage.setImageDrawable(dataBean.getComImage());

        //設定NumberPicker最小、最大值
        comNumberPick.setMinValue(1);
        comNumberPick.setMaxValue(iNumber);
        //設定NumberPicker監聽事件計算總金額
        comNumberPick.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker numberPicker, int i) {
                int pickValue  = comNumberPick.getValue();
                int iTotal = iPrice*pickValue;
                comTotal.setText("$" + iTotal);

            }
        });

        //結帳按鈕監聽事件
        Button btnComPay = view.findViewById(R.id.comButtonC);
        btnComPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int rod = (int)(Math.random()*1000000);
                String userAccount = dataBean.getsUserAccount();//用戶名
                int comNumber  = comNumberPick.getValue();//購買數量

                OrderBean orderData = new OrderBean();
                orderData.setId(dataBean.getsPKID());//id
                orderData.setUserAccount(userAccount);//用戶名
                orderData.setComName(dataBean.getsComName());//商品名稱
                orderData.setComNo(userAccount+rod);//商品編號
                orderData.setComNumber(comNumberPick.getValue());//購買數量
                orderData.setComTotal(iPrice*comNumber);//總金額
                orderData.setComImage(comImage.getDrawable());//圖片

                mainActivity.setOrderListData(orderData);


                Fragment orderDataF = new CheckoutFragment(mainActivity);

                RadioButton radioButton = mainActivity.findViewById(R.id.shoppingButtonID);
                radioButton.setChecked(true);
                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.FrameLayoutID, orderDataF).commit();


            }
        });


        Button buttonAddCart = view.findViewById(R.id.buttonAddCartID);
        buttonAddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int rod = (int)(Math.random()*1000000);
                String userAccount = dataBean.getsUserAccount();//用戶名
                int comNumber  = comNumberPick.getValue();//購買數量

                OrderBean orderData = new OrderBean();
                orderData.setId(dataBean.getsPKID());//id
                orderData.setUserAccount(userAccount);//用戶名
                orderData.setComName(dataBean.getsComName());//商品名稱
                orderData.setComNo(userAccount+rod);//商品編號
                orderData.setComNumber(comNumberPick.getValue());//購買數量
                orderData.setComTotal(iPrice*comNumber);//總金額
                orderData.setComImage(comImage.getDrawable());//圖片

                mainActivity.setOrderListData(orderData);

                Toast.makeText(mainActivity, "新增成功", Toast.LENGTH_SHORT).show();
            }
        });



        comName.setText(dataBean.getsComName());
        comPrice.setText("$"+iPrice);
        comNumber.setText("庫存:"+iNumber);
        comTotal.setText("$" + iPrice);

        return view;

    }
}