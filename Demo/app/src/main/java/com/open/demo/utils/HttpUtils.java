package com.open.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

/*
 * 测试类
 * 测试Https接口 post
 * 接收下属客户端上传样本,保存样本文件
 */
public class HttpUtils {
    private static class TrustAnyTrustManager implements X509TrustManager {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[] {};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    /**
     * post方式请求服务器(https协议)
     *
     * @param u
     *            请求地址
     * @param charset
     *            编码
     * @return
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     * @throws IOException
     * @throws NoSuchProviderException
     */
    public static String get(String u, String charset)
            throws NoSuchAlgorithmException, KeyManagementException, IOException, NoSuchProviderException {
        try {
            URL url = new URL(u);
            HttpURLConnection conn=(HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);

            System.out.println(conn.getResponseCode());
            System.out.println(conn.getResponseMessage());
            InputStream is = conn.getInputStream();
            if (is != null) {
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close();
                return new String(outStream.toByteArray(),"UTF-8");
            }
            return null;//conn.getResponseMessage();








//            TrustManager[] tm = { new TrustAnyTrustManager() };
//            // SSLContext sc = SSLContext.getInstance("SSL");
//            SSLContext sc = SSLContext.getInstance("SSL");
//            sc.init(null, tm, new java.security.SecureRandom());
//            URL console = new URL(u);
//
//            HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
//            conn.setSSLSocketFactory(sc.getSocketFactory());
//            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setRequestMethod("GET");
//            conn.connect();
////			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
////			out.write(content.getBytes(charset));
//            // 刷新、关闭
////			out.flush();
////			out.close();
//            InputStream is = conn.getInputStream();
//            if (is != null) {
//                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
//                byte[] buffer = new byte[1024];
//                int len = 0;
//                while ((len = is.read(buffer)) != -1) {
//                    outStream.write(buffer, 0, len);
//                }
//                is.close();
//                return new String(outStream.toByteArray(),"UTF-8");
//            }
        }  catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
//
//    public static void main(String[] args) throws ClientProtocolException, IOException {
//        try {
//            post("https://www.googleapis.com/youtube/v3/playlists?part=snippet&maxResults=50&channelId=UCJnvK2B5QvnT70ZK_an3eMg&key=AIzaSyCbl1b5Ga6q3u3vv41zt4egLAmnatkp5mU", "aaaaaaaaaaaaaaa", "UTF-8");
//        } catch (KeyManagementException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        } catch (NoSuchProviderException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }

}