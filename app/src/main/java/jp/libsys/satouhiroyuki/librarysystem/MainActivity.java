package jp.libsys.satouhiroyuki.librarysystem;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnDisp = (Button)findViewById(R.id.button);
        btnDisp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Sub 画面を起動
                Intent intent = new Intent(MainActivity.this, TakeQRCode.class);
                int requestCode = cafeConstants.READ_URL_CODE;
                startActivityForResult(intent, requestCode);
            }
        });
    }

    public void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == cafeConstants.READ_URL_CODE ){

            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){

                // 返却されてきたintentから値を取り出す
                String url = intent.getStringExtra( "key" );

                //URL形式かチェックする
                if(url.matches(cafeConstants.MATCH_URL)) {
                    //アマゾンを開く
                    this.openBrowser(url);
                }

                /*
                //数字かチェックする
                else if(url.matches(cafeConstants.MATCH_NUMBER)){
                    if(url.length() == 13) {
                        //本から取得したISBN-1がアマゾンのISBNとなるため計算する
                        String str = cafeConstants.AMAZON_SEARCH_URL + (Long.parseLong(url.substring(3, 13)) - 1);

                        //アマゾンを開く
                        this.openBrowser(str);
                    }
                }
                else{
                    this.sendMessage("読み込んだ情報に誤りがあります");
                }
                */
            }
        }
    }

    public void setUrl(String url){
        try {

            TextView text = (TextView) this.findViewById(R.id.textView2);
            text.setText(url);
        }catch(Exception ne) {
            this.sendMessage("読み込んだ情報に誤りがあります??");
        }
    }

    public void sendMessage(String message){

        //トーストを表示する
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();

    }

    public void openBrowser(String url) {

        //URLを起動する
        Uri uri = Uri.parse(url);
        Intent bro = new Intent(Intent.ACTION_VIEW,uri);

        try {
            startActivity(bro);
        }catch(Exception e) {
            this.sendMessage("システムエラーです。管理者の方に連絡してください");
        }
    }
}
