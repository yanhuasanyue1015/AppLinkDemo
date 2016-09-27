package richmj.com.applinkdemo;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by 张磊 on 2016/9/26.
 */
public class BaseActivity extends AppCompatActivity {
    protected void showToast(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }
    protected void i(String content){
        Log.i(getClass().getName(),content);
    }
}
