package getFoxKifu;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class getHttpRequest {
	
		 public static void main(String[] args) throws UnsupportedEncodingException { 		
		      startGetInputStreamThread();
		    } 
		 
		 private static void startGetInputStreamThread() {
			    BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
			    new Thread() {
			      public void run() {
			        try {
			        	//user_name xxxx
			          String line = "";
			          while ((line = inputReader.readLine()) != null) {
			        	  String[] params = line.trim().split(" ");
			        	  if(params.length>=2)
			        	  {
			        		  if(params[0].equals("user_name"))
			        		  {
			        			  String userName=params[1];
			        			  //http link response with json format user infos,hide the link for avoiding fox official forbade the request
				        	      String result=sendPost("http://****="+URLEncoder.encode(URLEncoder.encode(userName,"UTF-8"),"UTF-8")+"&accounttype=0&clienttype=0");
			        			  System.out.println(result);	
			        		  }
			        		  
			        		  if(params[0].equals("uid"))
			        		  {
			        			  if(params.length==3) {
			        				  String uid=params[1];
			        				  String lastCode=params[2];
			        				  //http link response with json format sgf game records,hide the link for avoiding fox official forbade the request
				        			  String result=sendPost("http://****="+URLEncoder.encode(URLEncoder.encode(lastCode,"UTF-8"),"UTF-8")+"&uid="+URLEncoder.encode(URLEncoder.encode(uid,"UTF-8"),"UTF-8")+"&RelationTag=0");
				        			  System.out.println(result);	
			        			  }
			        			  else {
			        			  String uid=params[1];
			        			//http link response with json format sgf game records,hide the link for avoiding fox official forbade the request
				        		 String result=sendPost("http://****&uid="+URLEncoder.encode(URLEncoder.encode(uid,"UTF-8"),"UTF-8")+"&RelationTag=0");
			        			  System.out.println(result);	
			        			  }
			        			  }
			        		  
			        		  if(params[0].equals("chessid"))
			        		  {			        			
			        			  String chessid=params[1];
			        			//http link response with json format one sgf game info,hide the link for avoiding fox official forbade the request
					        	  String result=sendPost("http://****="+URLEncoder.encode(URLEncoder.encode(chessid,"UTF-8"),"UTF-8"));
			        			  System.out.println(result);	
			        		  }
			        	  }
			          }
			        } catch (IOException e) {
			          e.printStackTrace();
			        }
			      }
			    }.start();
			  }
		 
		 private static String sendPost(String url) {
		        PrintWriter out = null;
		        BufferedReader in = null;
		        String result = "";
		        try {
		            URL realUrl = new URL(url);
		          
		            URLConnection conn = realUrl.openConnection();
		            conn.setRequestProperty("accept", "*/*");
		            conn.setRequestProperty("connection", "Keep-Alive");
		            conn.setRequestProperty("user-agent",
		                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
		            conn.setDoOutput(true);
		            conn.setDoInput(true);
		            out = //new PrintWriter(conn.getOutputStream());
		            new PrintWriter(
		                    new BufferedWriter(new OutputStreamWriter(conn.getOutputStream(), "UTF-8")));
		           // out.print(param);
		            out.flush();
		            in = new BufferedReader(
		                    new InputStreamReader(conn.getInputStream(),"UTF-8"));
		            String line;
		            while ((line = in.readLine()) != null) {
		                result += line;
		            }
		        } catch (Exception e) {
		            System.out.println(e);
		            e.printStackTrace();
		        }
		        finally{
		            try{
		                if(out!=null){
		                    out.close();
		                }
		                if(in!=null){
		                    in.close();
		                }
		            }
		            catch(IOException ex){
		                ex.printStackTrace();
		            }
		        }
		        return result;
		    }    
		
}
