package lta.commonproject.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import lta.commonproject.R;

/**
 * 二维码扫描
 *
 * @author LuTaiAn
 */
public class QRCodeActivity extends BaseActivity {
    @BindView(R.id.btn_qr_code)
    Button mScanBtn; //扫描按钮
    @BindView(R.id.btn_create_qr_code)
    Button mCreateBtn; //生成二维码
    @BindView(R.id.iv_result)
    ImageView mQRCodeIv; //生成的二维码
    /**
     * 入口方法
     * @param context
     */
    public static void launch(Context context) {
        Intent intent = new Intent(context,QRCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_qrcode;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
//        mScanBtn = (Button) findViewById(R.id.activity_qrcode);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initEvent() {
        mScanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(QRCodeActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap logo = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                Bitmap bitmap = EncodingUtils.createQRCode("第一个二维码图片",100,100,logo);
                mQRCodeIv.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                String result = bundle.getString("result");
                Toast.makeText(this, "result:" + result, Toast.LENGTH_LONG).show();
            }
        }
    }
}
