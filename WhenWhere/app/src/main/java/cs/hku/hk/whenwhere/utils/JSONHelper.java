package cs.hku.hk.whenwhere.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JSONHelper {

    //创建 SingleObject 的一个对象
    private static JSONHelper instance = new JSONHelper();

    //让构造函数为 private，这样该类就不会被实例化
    private JSONHelper(){}

    //获取唯一可用的对象
    public static JSONHelper getInstance(){
        return instance;
    }

    public String getJsonPage(String url) {
        HttpURLConnection conn_object = null;
        final int HTML_BUFFER_SIZE = 2*1024*1024;
        char htmlBuffer[] = new char[HTML_BUFFER_SIZE];
        try {
            URL url_object = new URL(url);
            conn_object = (HttpURLConnection) url_object.openConnection();
            conn_object.setInstanceFollowRedirects(true);
            BufferedReader reader_list = new BufferedReader
                    (new InputStreamReader(conn_object.getInputStream()));
            String HTMLSource = ReadBufferedHTML(reader_list, htmlBuffer,
                    HTML_BUFFER_SIZE);

            char[] html = HTMLSource.toCharArray();
            int HTMLEnd=HTML_BUFFER_SIZE;
            for(int i=0;i<HTML_BUFFER_SIZE;i++){
                //System.out.print(html[i]);
                if(html[i]=='}'){ //ASCII '}'
                    HTMLEnd=i+1;
                    break;
                }
            }
            HTMLSource=HTMLSource.substring(0,HTMLEnd); //为了缩减读到的jsonString的长度
            reader_list.close();
            return HTMLSource;
        } catch (Exception e) {
            return "Fail to login";
        } finally {
// When HttpClient instance is no longer needed,
// shut down the connection manager to ensure
// immediate deallocation of all system resources
            if (conn_object != null) {
                conn_object.disconnect();
            }
        }
    }

    public String ReadBufferedHTML(BufferedReader reader,
                                   char [] htmlBuffer, int bufSz) throws java.io.IOException {
        htmlBuffer[0] = '\0';
        int offset = 0;
        do {int cnt = reader.read(htmlBuffer, offset, bufSz - offset);
            if (cnt > 0) {
                offset += cnt;
            } else {
                break;
            }
        } while (true);
        return new String(htmlBuffer);
    }


}
