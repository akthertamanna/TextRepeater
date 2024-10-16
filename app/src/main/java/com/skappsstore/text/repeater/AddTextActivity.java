package com.skappsstore.text.repeater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatEditText;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

public class AddTextActivity extends AppCompatActivity {

    private Button mergeText;
    private TextInputEditText mainText;
    private AppCompatEditText beforeText, afterText;
    private String before , after, main;
    private EditText result;
    private TextView linesCount;
    private int separateWith =1;
    private RadioButton commaRd, lineRd;
    private ImageView copyBtn, shareBtn, shareWhatBtn;
    private Button clearBtn;
    private AppCompatCheckBox withSpace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        getSupportActionBar().setTitle("Join Text Before/After");
//        getSupportActionBar().setSubtitle("Before/After");

        mainText = findViewById(R.id.mainText);
        mergeText = findViewById(R.id.mergeText);
        beforeText = findViewById(R.id.beforeText);
        afterText = findViewById(R.id.afterText);
        result = findViewById(R.id.result);
        linesCount = findViewById(R.id.linesCount);
        clearBtn = findViewById(R.id.clearBtn);

        withSpace = findViewById(R.id.withSpace);

        commaRd = findViewById(R.id.commaRd);
        lineRd = findViewById(R.id.lineRd);

        commaRd.setChecked(true);
        copyBtn = findViewById(R.id.copyBtn);
        shareBtn = findViewById(R.id.shareBtn);
        shareWhatBtn = findViewById(R.id.shareWhatBtn);

        TextView textView = findViewById(R.id.instructionID);
        SpannableString content = new SpannableString(getResources().getString(R.string.howToUseMerge));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(content);

        textView.setOnClickListener(v -> {
            Helper.showInstruction(AddTextActivity.this,getResources().getString(R.string.mergeTextInst));
        });


        copyBtn.setOnClickListener(v -> {
            String mainTxt = result.getText().toString().trim();
            if (mainTxt.equals("")){
                    Helper.showAlert(AddTextActivity.this, "Merge/Join Text first to copied!");
            }else {
                Helper.copyText(AddTextActivity.this, mainTxt);
            }
        });

        shareWhatBtn.setOnClickListener(v -> {
            String mainTxt = result.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(AddTextActivity.this, "Merge/Join Text first to share with WhatsApp!");
            }else {
                Helper.shareWithWhatsapp(AddTextActivity.this, mainTxt);
            }
        });

        shareBtn.setOnClickListener(v -> {
            String mainTxt = result.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(AddTextActivity.this, "Merge/Join Text first to share!");
            }else {
                Helper.share(AddTextActivity.this, mainTxt,0);
            }
        });

        clearBtn.setOnClickListener(v -> {
            result.setText("");
            mainText.setText("");
        });

        mergeText.setOnClickListener(v -> {

            result.setText("");
            before = beforeText.getText().toString().trim();
            after = afterText.getText().toString().trim();

            main = mainText.getText().toString().trim();
            int line = mainText.getLineCount();

            if (main.equals("")){
                Helper.showAlert(AddTextActivity.this, "Please enter text first");
            } else if (!before.equals("") || !after.equals("")){
                if(line>100) {
                    Helper.showAlert(AddTextActivity.this, "Text must be between 1 to 100 lines");
                }else{
                    String[] st = new String[0];
                    if (separateWith==1){
                        st = main.split(",");
                    }else if(separateWith==2){
                        st = main.split("\n");
                    }
                    StringBuilder stringBuilder = new StringBuilder();

                    try {
                        for (String s : st) {
                            if (withSpace.isChecked()) {
                                
                                stringBuilder.append(before).append(" ").append(s).append(" ").append(after).append("\n");
                            } else {

                                stringBuilder.append(before).append(s).append(after).append("\n");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Helper.showAlert(AddTextActivity.this, "Please try again");
                    }

                    result.append(stringBuilder);
                }
            }else{
                Helper.showAlert(AddTextActivity.this, "Please enter text either for before or after");
            }



        });
        mainText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                linesCount.setText("Lines: "+mainText.getLineCount());
            }
            @Override
            public void afterTextChanged(Editable s) {
                main = mainText.getText().toString();
                if (main.equals("")){
                    linesCount.setText("Lines: 0");
                }

            }
        });


    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {

            case R.id.commaRd:
                if (checked)
                    separateWith =1;
                    break;
            case R.id.lineRd:
                if (checked)
                    separateWith =2;
                    break;

        }
    }
}