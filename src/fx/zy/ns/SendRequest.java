package fx.zy.ns;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import net.sf.json.JSONObject;

public class SendRequest {
	public static String sendByGet(JFrame frame,String url, List<String> par_name, List<String> par_value)
	{
		String response = "";
		
		BufferedReader in = null;
		
		String complete_url = "";
		assert par_name.size()==par_value.size();
		
		for(int i=0; i<par_name.size(); i++)
		{
			if(i!=0){
				complete_url += "&";
			}
			complete_url += par_name.get(i);
			complete_url += "=";
			complete_url += par_value.get(i);
		}
		try {
			complete_url = url + "?" + complete_url;
		 	System.out.println("complete_url:"+complete_url);

		 	URL realUrl = new URL(complete_url);
			URLConnection connection = realUrl.openConnection();
			
			connection.setRequestProperty("accept", "*/*");
	        connection.setRequestProperty("connection", "Keep-Alive");
	        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
	
	        // 建立实际的链接
	        connection.connect();
	        
	        // 获取响应头字段
	        //Map<String, List<String>> map = connection.getHeaderFields();

	        // 定义 BufferedReader输入流来读取URL的响应
	        in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	
	        String line;
	        while ((line = in.readLine()) != null) 
	        {
	            response += line;
	        }
		}catch(Exception e){
			System.out.println("发送get请求异常" + e);
			Util.getDialog(frame, e.toString());
			response = "ERROR:"+e.toString();
            e.printStackTrace();
		}finally{
			try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
		return response;
	}
	public static String sendByPost(JFrame frame,String url, List<String> par_name, List<String> par_value, String contents)
	{
		String response = "";
		
		BufferedReader in = null;
		

		String complete_url = "";
		assert par_name.size()==par_value.size();
		
		for(int i=0; i<par_name.size(); i++)
		{
			if(i!=0){
				complete_url += "&";
			}
			complete_url += par_name.get(i);
			complete_url += "=";
			complete_url += par_value.get(i);
		}
		try {
			complete_url = url + "?" + complete_url;
		 	System.out.println("complete_url:"+complete_url);
		 	
			JSONObject json_obj = JSONObject.fromObject(contents);
            byte[] requestStringBytes = json_obj.toString().getBytes("utf-8");
            
			URL realUrl = new URL(complete_url);

			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			
			connection.setDoOutput(true); 
            connection.setDoInput(true);
			connection.setRequestMethod("POST"); 
			connection.setUseCaches(false);  
			connection.setRequestProperty("Content-length", "" + requestStringBytes.length);
	        connection.setRequestProperty("Content-Type", "application/json;encoding=utf-8"); 
	        connection.setRequestProperty("Charset", "utf-8");
	
	        connection.connect();
	        
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream()); 
            out.write(requestStringBytes);
            out.flush(); 
            out.close(); 
            
	        Map<String, List<String>> map = connection.getHeaderFields();
	        
	        in = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	
	        String line;
	        while ((line = in.readLine()) != null) 
	        {
	            response += line;
	        }
		}catch(Exception e){
        	Util.getDialog(frame, e.toString());
			response = "ERROR:"+e.toString();
            e.printStackTrace();
		}finally{
			try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
		}
		
		return response;
	}

	@SuppressWarnings("finally")
	public static String httpUpload(JFrame frame,String actionUrl, String[] uploadFilePaths){
		String response = "";
		String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            // 统一资源
            URL url = new URL(actionUrl);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // 设置是否向httpUrlConnection输出
            httpURLConnection.setDoOutput(true);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码连接参数
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 设置请求内容类型
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // 设置DataOutputStream
            ds = new DataOutputStream(httpURLConnection.getOutputStream());
            for (int i = 0; i < uploadFilePaths.length; i++) {
                String uploadFile = uploadFilePaths[i];
                String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
                ds.writeBytes(twoHyphens + boundary + end);
                ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + i + "\";filename=\"" + filename
                        + "\"" + end);
                ds.writeBytes(end);
                FileInputStream fStream = new FileInputStream(uploadFile);
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                while ((length = fStream.read(buffer)) != -1) {
                    ds.write(buffer, 0, length);
                }
                ds.writeBytes(end);
                /* close streams */
                fStream.close();
            }
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            ds.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
            	Util.getDialog(frame, "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
//                throw new Exception("HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }
            response = resultBuffer.toString();
        } catch (Exception e) {
        	Util.getDialog(frame, e.toString());
			response = "ERROR:"+e.toString();
            e.printStackTrace();
        } finally {
                if (ds != null) {
                    try {
                        ds.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                return response;
            }
    }
	
	/**
     * 
     * @param urlPath
     *            下载路径
     * @param downloadDir
     *            下载存放目录
     * @return 返回下载文件
     */
    @SuppressWarnings("finally")
	public static String downloadFile(JFrame frame,String urlPath, String downloadDir) {
    	String response = "";
    	File file = null;
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();

            // 文件大小
            int fileLength = httpURLConnection.getContentLength();

            // 文件名
            String filePathUrl = httpURLConnection.getURL().getFile();
            String fileFullName = filePathUrl.substring(filePathUrl.lastIndexOf(File.separatorChar) + 1);

            System.out.println("file length---->" + fileLength);

            URLConnection con = url.openConnection();

            BufferedInputStream bin = new BufferedInputStream(httpURLConnection.getInputStream());

            String path = downloadDir + File.separatorChar + fileFullName;
            response = "path";
            file = new File(path);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            OutputStream out = new FileOutputStream(file);
            int size = 0;
            int len = 0;
            byte[] buf = new byte[1024];
            while ((size = bin.read(buf)) != -1) {
                len += size;
                out.write(buf, 0, size);
                // 打印下载百分比
                // System.out.println("下载了-------> " + len * 100 / fileLength +
                // "%\n");
            }
            bin.close();
            out.close();

            response = "下载完成";
        } catch (Exception e) {
            // TODO Auto-generated catch block
        	Util.getDialog(frame, e.toString());
			response = "ERROR:"+e.toString();
            e.printStackTrace();
        } finally {
            return response;
        }
    }

}
