package google.example.com.googletraning.utils;

import android.util.Log;

/**
 * @author lizhehua9@wanda.cn (lzh)
 * @date 16/5/26 18:36
 */
public class SLogUtil {

    private final static String NULL_TIPS = "";

    private static String[] wrapperContent(Object objectMsg) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        int index = 5; //这个数值是根据调试出来的，无法根据当前栈判断在哪一层数
        String className = stackTrace[index].getFileName();
        String methodName = stackTrace[index-1].getMethodName();
        int lineNumber = stackTrace[index-1].getLineNumber();
        String methodNameShort = methodName.substring(0, 1) + methodName.substring(1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("  [(").append(className).append(":").append(lineNumber).append(")").append("-->").append(methodName).append("]");

        String tag = className;
        String msg = (objectMsg == null) ? NULL_TIPS : objectMsg.toString();
        String headString = stringBuilder.toString();
        return new String[]{tag, msg, headString};
    }

    public static void d(Object objectMsg){
        String[] contents = wrapperContent(objectMsg);
        String tag = contents[0];
        String msg = contents[1];
        String location = contents[2];

        Log.e(tag, msg + location);
    }
}
