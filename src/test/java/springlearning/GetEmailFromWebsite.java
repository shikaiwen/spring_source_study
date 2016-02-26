package springlearning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * ftpµÇÂ¼Ïà¹Ø:http://www.programcreek.com/java-api-examples/index.php?api=sun.net.ftp.FtpClient
 * @author kevin
 *
 */
public class GetEmailFromWebsite {
	static Logger logger = Logger.getLogger("GetEmailFromWebsite");

	public static void main(String[] args) throws Throwable {
		
		
		getEmailAddr("http://tieba.baidu.com/p/1216797702");
	
//		getEmailAddr("ftp://124.232.137.8/");

	}
	
	public static final String DEFAULT_ENCODING = "UTF-8";
	public static void getEmailAddr(String url) throws Exception{
		URL siteUrl = new URL(url);
//		HttpURLConnection conn = (HttpURLConnection)siteUrl.openConnection();
		URLConnection conn = siteUrl.openConnection();
//		InputStream is = conn.getInputStream();
		String contentType = conn.getContentType();
		String parsedCharset = null;
        if(contentType != null){
        	logger.log(Level.INFO, "found contentType " + contentType );
        	if(contentType.lastIndexOf("=") != -1){
        		String charset = contentType.substring(contentType.lastIndexOf("=")+1 );
        		parsedCharset = Charset.forName(charset).name();
        		
        	}
        }
        
        String strCharset = null;
        if(parsedCharset == null){
        	logger.log(Level.INFO, "not found charset , use default " + DEFAULT_ENCODING);
        	strCharset = DEFAULT_ENCODING;
        }else{
        	logger.log(Level.INFO, "use contentType charset , " + parsedCharset);
        	strCharset = parsedCharset;
        }
        
        List<String> emailList = new LinkedList<String>();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),strCharset));
        String read = null;
        Pattern p = Pattern.compile("[0-9a-zA-Z]{2,}@[0-9a-zA-Z]{1,}\\.[0-9a-zA-Z]{1,}");
        while((read = br.readLine()) != null){
        	Matcher matcher = p.matcher(read);
        	while(matcher.find()){
        		String email = matcher.group();
        		emailList.add(email);
        	}
        }
        
        
        
        System.out.println(emailList);
        
//		String contentEncoding = conn.getContentEncoding();
//		Object content = conn.getContent();
//		Map<String,List<String>> headerFileds = conn.getHeaderFields();
//		
//		if(headerFileds != null){
//			
//			for(String key : headerFileds.keySet()){
//				List<String> values = headerFileds.get(key);
//				System.out.println(key + " : " + values);
//			}
//			
//		}
		
	}
}
