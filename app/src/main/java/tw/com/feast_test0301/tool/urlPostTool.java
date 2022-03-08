package tw.com.feast_test0301.tool;

import org.json.JSONArray;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class urlPostTool extends Thread {
    private String url = null;
    private HashMap<String, JSONArray> postData;
    public NetEventListener OnServerAnswerListener;

    public urlPostTool(String url, HashMap<String, JSONArray> postData){
        this.url =url;
        this.postData = postData;
    }

    //宣告監控事件的抽象方法，以便後續調用方法內的實作
    public interface NetEventListener {
        public void linkStart();
        public void linkEnd(byte[] dataByte);
    }

    /**
     * 繼承並實作執行續內容
     */
    @Override
    public void run() {
        OnServerAnswerListener.linkStart();
        urlLink(url,postData);
        return;
    }

    /**
     * 連接httpURL並取得httpURL的內容
     * @param url URL位置
     */
    public void urlLink(String url,HashMap<String, JSONArray> postData){
        HttpURLConnection conn = null;

        URL Server;
        System.out.println(url);
        try {
            Server = new URL(url);
            conn = (HttpURLConnection) Server.openConnection();
            //設定Timeout
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            //設定Method的方式(Post)
            conn.setRequestMethod("POST");
            //將InputStream跟OutputStream開啟
            conn.setDoInput(true);
            conn.setDoOutput(true);
            //設定傳送資料的content-type(資料型態與編碼)
            conn.setRequestProperty("content-type","application/x-www-form-urlencoded; charset=utf-8");
            //取得post Output
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    //設定寫入資料的編碼
                    new OutputStreamWriter(os, "UTF-8"));
            //寫入資料， getPostDataString是下面的方法，用來將輸入的資料轉換為http內的格式，傳入HashMap
            writer.write(getPostDataString(postData));
            //傳送
            writer.flush();
            writer.close();
            os.close();
            //取得連結
            conn.connect();
            InputStream is = conn.getInputStream();

            byte[] inDateChar = new byte[1024];
            ArrayList<Byte> pool = new ArrayList<Byte>();
            int loadSize=is.read(inDateChar);
            while (loadSize!=-1){
                for (int i=0;i<loadSize;i++){
                    pool.add(inDateChar[i]);
                }
                loadSize = is.read(inDateChar);
            }
            byte[] allDate =new byte[pool.size()];
            for(int i=0;i<pool.size();i++){
                allDate[i]= pool.get(i);
            }
            OnServerAnswerListener.linkEnd(allDate);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                conn.disconnect();
        }
        return;
    }

    /**
     * 將傳入的資料轉換為Https傳輸格式的方法
     * @param params 傳入的資料。其形態為HashMap
     * @return 會回傳一個Https傳輸格式的資料
     * @throws UnsupportedEncodingException
     */
    private String getPostDataString(HashMap<String, JSONArray> params) throws UnsupportedEncodingException {
        //宣告一個空的字串(StringBuilder)，"暫時"用來放轉換過後的資料
        StringBuilder result = new StringBuilder();
        //宣告一個布林用來判斷是否為第一筆資料
        boolean first = true;
        //用for迴圈將HashMap內的資料取出
        for(Map.Entry<String, JSONArray> entry : params.entrySet()){
            //判斷HashMap內的資料是否為第一筆資料
            if (first) {
                first = false;
            }else {
                result.append("&");
            }
            //取得HashMap內的Key值，並傳入到result("暫時"用來放轉換過後的資料)裡面。
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            //取得HashMap內的Value值，並傳入到result("暫時"用來放轉換過後的資料)裡面。
            result.append(URLEncoder.encode(entry.getValue().toString(), "UTF-8"));
        }
        //將StringBuilder裡面的資料轉成字串(String)回傳。
        return result.toString();
    }
}
