package lta.commonproject.common;

import android.os.Handler;
import android.os.Message;

/**
 * @author: LuTaiAn
 * @ClassName:
 * @Description:
 * @date: 2016/9/14
 */
public class ExitHandler extends Handler {
    public final static int EXIT_MSG_WHAT = 1;
    public final static int EXIT_MSG_OUT_TIME_WHAT = 2;
    private boolean isBackClicked = false;
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case EXIT_MSG_OUT_TIME_WHAT:
                isBackClicked = false;
                break;
            case EXIT_MSG_WHAT:
                isBackClicked = true;
                break;
        }
    }

    public boolean isBackClicked() {
        return isBackClicked;
    }
}
