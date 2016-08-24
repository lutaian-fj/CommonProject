package lta.commonproject.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import lta.commonproject.R;

public class LoginActivity extends AppCompatActivity {

    private Button button;
    private TextInputLayout mUserNameTil;
    private TextInputLayout mPswTil;
    private EditText mUserNameEdit;
    private EditText mPswEdit;
    private String mUserName;
    private String mPsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = (Button) findViewById(R.id.login_btn);
        mUserNameTil = (TextInputLayout) findViewById(R.id.username_til);
        mPswTil = (TextInputLayout) findViewById(R.id.password_til);
        mUserNameTil.setErrorEnabled(true);
        mPswTil.setErrorEnabled(true);

        mUserNameEdit = (EditText) findViewById(R.id.username_edit);
        mPswEdit = (EditText) findViewById(R.id.password_edit);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUserName = mUserNameEdit.getText().toString().trim();
                mPsw = mPswEdit.getText().toString().trim();

                if(TextUtils.isEmpty(mUserName)) {
                    mUserNameTil.setError("用户名不能为空");
                    return;
                }else if(TextUtils.isEmpty(mPsw)) {
                    mPswTil.setError("密码不能为空");
                    return;
                }else {
                    MainActivity.launch(LoginActivity.this);
                }

            }
        });
    }
}
