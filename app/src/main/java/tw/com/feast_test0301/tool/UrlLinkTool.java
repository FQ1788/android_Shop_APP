package tw.com.feast_test0301.tool;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class UrlLinkTool extends Thread {
    private String url = null;

    public NetEventListener OnServerAnswerListener;

    public UrlLinkTool(String url){
        this.url =url;
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
        urlLink(url);
        return;
    }

    /**
     * 連接httpURL並取得httpURL的內容
     * @param url URL位置
     */
    public void urlLink(String url){
        HttpURLConnection conn = null;
        InputStream is = null;
        URL Server;
        System.out.println(url);
        try {
            Server = new URL(url);
            conn = (HttpURLConnection) Server.openConnection();
            conn.connect();
            is = conn.getInputStream();

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
            try {
                is.close();
                conn.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }
}
