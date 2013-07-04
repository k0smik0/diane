package net.iubris.dianeroboguicesample.task;

import net.iubris.diane.searcher.aware.full.FullAwareSearcher;
import android.util.Log;
import android.widget.TextView;

public class ExceptionUtils {
	
	public static void showException(Exception exception, TextView textView) {
		String exceptionMessage = getExceptionMessage(exception);
		Log.d("DianeRoboSampleSearchAsyncTask:77", "onException("+exception.getClass().getSimpleName()+"): "+exceptionMessage);
//		Toast.makeText(context, exceptionMessage, Toast.LENGTH_SHORT).show();
		textView.setText( textView.getText()
				+exceptionMessage+"\n\n");
	}
	public static void showException(Exception exception, String suffix, TextView textView) {
		String exceptionMessage = getExceptionMessage(exception);
		Log.d("DianeRoboSampleSearchAsyncTask:82", "onException("+exception.getClass().getSimpleName()+"): "+exceptionMessage);
//		Toast.makeText(context,exceptionMessage+"\n\n"+suffix, Toast.LENGTH_SHORT).show();
		textView.setText( textView.getText()
				+exceptionMessage+"\n"+suffix+"\n\n");
	}
	public static void showException(Exception exception, TextView textView, FullAwareSearcher<String> fullAwareSearcher) {
		showException(exception, textView);
		fullAwareSearcher.resetSearchState();
	}
	public static void showException(Exception exception, String suffix, TextView textView, FullAwareSearcher<String> fullAwareSearcher) {
		showException(exception, suffix, textView);
		fullAwareSearcher.resetSearchState();
	}
	
	
	
	public static String getExceptionMessage(Exception e) {
		Throwable cause = e.getCause();
		String message = e.getMessage();
		if (cause!=null)
			message.concat("\ncaused from: "+cause.getMessage());
		return message;
	}
}
