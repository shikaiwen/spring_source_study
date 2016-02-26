package springlearning;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

@SuppressWarnings("serial")
class LoggingException extends Exception{
	
	private static Logger logger = Logger.getLogger("LogginException");
	
	public LoggingException(){
		StringWriter trace = new StringWriter();
		this.printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}
}

public class LoggingExceptionTest {

	public static void main(String[] args) {
		try{
			throw new LoggingException();
		}catch(LoggingException e){
			System.err.println("Caught " + e);
		}
	}
	
}
