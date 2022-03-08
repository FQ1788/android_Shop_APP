package tw.com.feast_test0301;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tw.com.feast_test0301.fragment.AddUserFragment;
import tw.com.feast_test0301.fragment.CheckoutFragment;
import tw.com.feast_test0301.fragment.ComAllDataFragment;
import tw.com.feast_test0301.fragment.LoginFragment;
import tw.com.feast_test0301.fragment.OrderHistoryFragment;
import tw.com.feast_test0301.fragment.UserCenterFragment;
import tw.com.feast_test0301.tool.UrlLinkTool;
import tw.com.feast_test0301.utils.DataBean;
import tw.com.feast_test0301.utils.OrderBean;

public class MainActivity extends AppCompatActivity {
    Button inOrOutBt;
    String userName;
    private List<OrderBean> listData = new ArrayList<OrderBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inOrOutBt = findViewById(R.id.loginID);
        Button userButton = findViewById(R.id.userButtonID);
        Button homeButton = findViewById(R.id.homeButtonID);
        Button shoppingButton = findViewById(R.id.shoppingButtonID);
        Button orderButton = findViewById(R.id.orderButtonID);
        //會員按鈕
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userName == null) {
                    switchFragment(new LoginFragment());
                } else {
                    switchFragment(new UserCenterFragment());
                }
            }
        });
        //首頁按鈕
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
        //購物車按鈕
        shoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new CheckoutFragment(MainActivity.this));
            }
        });
        //訂單查詢按鈕
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(new OrderHistoryFragment());
            }
        });
        getData();
    }

    /**
     * 查詢訂單
     */
    public void toOrder(View v) {
        switchFragment(new OrderHistoryFragment());
    }

    /**
     * 會員註冊畫面
     *
     * @param v
     */
    public void addUserView(View v) {
        switchFragment(new AddUserFragment());
    }

    /**
     * 登入畫面
     *
     * @param v loginRun為視窗
     */
    public void loginORlogout(View v) {
        String inORout = inOrOutBt.getText().toString();
        String login = getString(R.string.login);
        String logout = getString(R.string.logout);
        if (login.equals(inORout)) {
            switchFragment(new LoginFragment());
        } else if (logout.equals(inORout)) {
            userName = null;
            inOrOutBt.setText(R.string.login);
            Toast.makeText(MainActivity.this, "登出成功", Toast.LENGTH_LONG).show();
        }
        return;
    }

    /**
     * 登入判斷
     */
    public void login(View v) {
        String inName = ((TextView) findViewById(R.id.InUserNameID)).getText().toString();
        String inPass = ((TextView) findViewById(R.id.InUserPassID)).getText().toString();
        ProgressBar loadBar = findViewById(R.id.loadBarID);
        String url = "http://SERVER?userName=" + inName + "&userPass=" + inPass;
        UrlLinkTool linkTool = new UrlLinkTool(url);
        linkTool.OnServerAnswerListener = new UrlLinkTool.NetEventListener() {
            @Override
            public void linkStart() {
                viewRun(() -> {
                    loadBar.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void linkEnd(byte[] dataByte) {
                String dataString = new String(dataByte).trim();
                viewRun(() -> {
                    loadBar.setVisibility(View.INVISIBLE);
                });
                if ("OK".equals(dataString)) {
                    viewRun(() -> {
                        inOrOutBt.setText(R.string.logout);
                        Toast.makeText(MainActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
                        switchFragment(new UserCenterFragment());
                    });
                    //存取帳號
                    userName = inName;

                } else if ("NO".equals(dataString)) {
                    viewRun(() -> {
                        Toast.makeText(MainActivity.this, "帳號密碼錯誤", Toast.LENGTH_SHORT).show();
                    });
                } else {
                    viewRun(() -> {
                        Toast.makeText(MainActivity.this, "連線異常", Toast.LENGTH_SHORT).show();
                    });
                }
            }
        };
        linkTool.start();
        return;
    }

    /**
     * 取得所有商品資料的方法
     */
    public void getData() {
        UrlLinkTool linkTool = new UrlLinkTool("http://10.0.2.2:8080/ProjectDemo/DemoTest?state=getData");
        linkTool.OnServerAnswerListener = new UrlLinkTool.NetEventListener() {
            @Override
            public void linkStart() {
                viewRun(() -> {
                    Toast.makeText(MainActivity.this, "資料載入中....", Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void linkEnd(byte[] dataByte) {
                try {
                    JSONObject comDataObj = new JSONObject(new String(dataByte));
                    switchFragment(new ComAllDataFragment(comDataObj,MainActivity.this));
                    viewRun(() -> {
                        viewRun(() -> {
                            Toast.makeText(MainActivity.this, "載入成功", Toast.LENGTH_SHORT).show();
                        });
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        linkTool.start();
        return;
    }

    /**
     * 傳送註冊資料
     *
     * @param v
     */
    public void addUser(View v) {

    }

    /**
     * 切畫頁面的方法
     *
     * @param fragment 切換的fragment
     */
    public void switchFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.FrameLayoutID, fragment)
                .commit();
        return;
    }

    public List<OrderBean> getOrderListData(){
        return listData;
    }


    public void setOrderListData(OrderBean orderBean){
            listData.add(orderBean);
        System.out.println(listData);
    }

    public void removeList(){
        listData = new ArrayList<OrderBean>();
    }

    public void removeComData(int i){
        listData.remove(i);
    }


    public void viewRun(Runnable r) {
        MainActivity.this.runOnUiThread(r);
        return;
    }
}