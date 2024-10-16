package com.skappsstore.text.repeater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RepeatedActivity extends AppCompatActivity {

    private EditText result;
    private Button repeatMainText , clearBtn;
    private TextInputEditText mainText;
    private AppCompatEditText howManyTxt;
    private ImageView copyBtn, shareBtn, shareWhatBtn;
    private int i = 0;
//    private TextView byteID;
    private AppCompatCheckBox withSpace, withNewLine, withVerSpace;
    private ExecutorService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeated);
        getSupportActionBar().setTitle("Repeat Text");

        result = findViewById(R.id.result);

        repeatMainText = findViewById(R.id.repeatMainText);
        mainText = findViewById(R.id.mainText);
        howManyTxt = findViewById(R.id.howManyTxt);
        clearBtn = findViewById(R.id.clearBtn);
//        byteID = findViewById(R.id.byteID);


        copyBtn = findViewById(R.id.copyBtn);
        shareBtn = findViewById(R.id.shareBtn);
        shareWhatBtn = findViewById(R.id.shareWhatBtn);


        withSpace = findViewById(R.id.withSpace);
        withNewLine = findViewById(R.id.withNewLine);
        withVerSpace = findViewById(R.id.withVerSpace);


        TextView textView = findViewById(R.id.instructionID);
        SpannableString content = new SpannableString(getResources().getString(R.string.howToUseRepeat));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        textView.setOnClickListener(v -> {
            Helper.showInstruction(RepeatedActivity.this,getResources().getString(R.string.repeatedTextInst));
        });


        repeatMainText.setOnClickListener(v -> {
            result.setText("");
            String mainTxt = mainText.getText().toString().trim();
            String howMan = howManyTxt.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(RepeatedActivity.this, "Please enter text first");
            } else if (howMan.equals("")){
                Helper.showAlert(RepeatedActivity.this, "Please enter how many times you want to repeat your text");
            }else if (Integer.parseInt(howMan)<0 || Integer.parseInt(howMan)>5000){
                Helper.showAlert(RepeatedActivity.this, "Please enter repeated times less than or equal to 5000");
            }else{
                callThread(mainTxt, Integer.parseInt(howMan));
                repeatMainText.setText("Please wait..");
            }


        });

        copyBtn.setOnClickListener(v -> {
            String mainTxt = result.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(RepeatedActivity.this, "Generate text first to copied!");
            }else {
                Helper.copyText(RepeatedActivity.this, mainTxt);
            }
        });

        shareWhatBtn.setOnClickListener(v -> {
            String mainTxt = result.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(RepeatedActivity.this, "Generate text first to share with WhatsApp!");
            }else {
                Helper.shareWithWhatsapp(RepeatedActivity.this, mainTxt);
            }
        });

        shareBtn.setOnClickListener(v -> {
            String mainTxt = result.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(RepeatedActivity.this, "Generate text first to share!");
            }else {
                Helper.share(RepeatedActivity.this, mainTxt,0);
            }
        });

        clearBtn.setOnClickListener(v -> {
            result.setText("");
            mainText.setText("");
        });
    }

    public void generateRepeatedText(String mainTxt, int howMany) {

        StringBuilder stringBuilder  =new StringBuilder();

        try {
            for (int i =0; i< howMany; i++) {

                if (withVerSpace.isChecked()) {
                    stringBuilder.append(mainTxt + "\n\n");
                } else {
                    if (withNewLine.isChecked()) {
                        stringBuilder.append(mainTxt + "\n");
                    } else if (withSpace.isChecked()) {
                        stringBuilder.append(mainTxt + " ");
                    } else {
                        stringBuilder.append(mainTxt);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.append(stringBuilder);
        repeatMainText.setText("Repeat Text");
        service.shutdownNow();

    }

    public void callThread(String mainTxt, int i){
        service = Executors.newSingleThreadExecutor();

        service.execute(() -> runOnUiThread(new Runnable() {
            @Override
            public void run() {
                generateRepeatedText(mainTxt, i);

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