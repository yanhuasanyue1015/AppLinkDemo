package richmj.com.applinkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends BaseActivity {

    private EditText edtContent;
    private Button btnGeyUrlMes;
    private LinkView linkv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.linkv = (LinkView) findViewById(R.id.linkv);
        btnGeyUrlMes = (Button) findViewById(R.id.btnGeyUrlMes);
        edtContent = (EditText) findViewById(R.id.edtContent);
        btnGeyUrlMes.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btnGeyUrlMes:
                    linkv.setUrl(edtContent.getText().toString());
                    break;
            }
        }
    };

    private void showUrlInfo(String url) {
    }

}
