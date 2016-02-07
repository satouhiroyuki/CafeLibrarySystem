package jp.libsys.satouhiroyuki.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.CompoundBarcodeView;

import java.util.List;

public class TakeQRCode extends AppCompatActivity {

    private CompoundBarcodeView mBarcodeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_qrcode);

        mBarcodeView = (CompoundBarcodeView)findViewById(R.id.barcodeView);
        mBarcodeView.decodeSingle(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult barcodeResult) {

                TextView textView = (TextView) findViewById(R.id.textView);
                textView.setText(barcodeResult.getText());

                //取得したURLをMainActitvyに戻す
                Intent intent = new Intent();
                intent.putExtra("key", barcodeResult.getText());
                setResult(Activity.RESULT_OK, intent);
                //Actitvyを返す
                finish();
            }

            @Override
            public void possibleResultPoints(List<ResultPoint> list) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mBarcodeView.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBarcodeView.pause();
    }
}
