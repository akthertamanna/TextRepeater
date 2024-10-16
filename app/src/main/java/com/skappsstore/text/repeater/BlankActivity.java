package com.skappsstore.text.repeater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BlankActivity extends AppCompatActivity {

    private TextInputEditText textLimit;
    private AppCompatCheckBox withNewLine;
    private String blankTxt ="";
    private ExecutorService service;
    private ImageView copyBtn;
    private TextView noticeID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);
        getSupportActionBar().setTitle("Blank Text");

        textLimit = findViewById(R.id.textLimit);
        withNewLine = findViewById(R.id.withNewLine);

        copyBtn = findViewById(R.id.copyBtn);
        noticeID = findViewById(R.id.noticeID);

        SpannableString sp =new SpannableString(getString(R.string.notice));
        ForegroundColorSpan spc = new ForegroundColorSpan(Color.RED);
        sp.setSpan(spc,0,5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        noticeID.setText(sp);
        copyBtn.setOnClickListener(v -> {

            if (textLimit.getText().toString().trim().equals("")){
                Helper.showAlert(BlankActivity.this, "Please enter how many times you want to generate blank text");
            }else if (Integer.parseInt(textLimit.getText().toString().trim()) >=5000){
                Helper.showAlert(BlankActivity.this, "Please enter number less than or equal to 5000");
            }else{
                int howMany = Integer.parseInt(textLimit.getText().toString().trim());
                callThread(howMany, "copy");
            }

        });

        TextView textView = findViewById(R.id.instructionID);
        SpannableString content = new SpannableString(getResources().getString(R.string.howToUseBlank));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        textView.setOnClickListener(v -> {
            Helper.showInstruction(BlankActivity.this,getResources().getString(R.string.blankTextInst));
        });


    }


    public void generateRepeatedText(int howMany , String type){
        StringBuilder stringBuilder  =new StringBuilder();
        blankTxt ="";
        for (int i =0; i<howMany;i++){

            if (withNewLine.isChecked()){
                stringBuilder.append(" "+"\n");
            }else{
                stringBuilder.append(" ");
            }


        }
        blankTxt = String.valueOf(stringBuilder);

        if (type.equals("copy")){
            Helper.copyText(BlankActivity.this, blankTxt);
        }else if (type.equals("shr")){
            Helper.share(BlankActivity.this, blankTxt, 0);
        }else{
            Helper.shareWithWhatsapp(BlankActivity.this, blankTxt);
        }
    }

    public void callThread(int howmany, String type){
        service = Executors.newSingleThreadExecutor();

        service.execute(() -> runOnUiThread(new Runnable() {
            @Override
            public void run() {

                generateRepeatedText(howmany, type);

            }
        }));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (service != null){
            service.shutdown();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (service != null){
            service.shutdown();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (service != null){
            service.shutdown();
        }
    }

}