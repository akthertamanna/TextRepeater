package com.skappsstore.text.repeater;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.textfield.TextInputEditText;

public class CountActivity extends AppCompatActivity {

    private TextInputEditText mainText;
    private TextView word, character, lines, sentences;
    private String mainData;
    private int characters = 0, words=0, sentenceCount=0;
    private Button clearBtn;
    private ImageView copyBtn, shareBtn, shareWhatBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        getSupportActionBar().setTitle("Count Text");

        mainText = findViewById(R.id.mainText);
        word = findViewById(R.id.word);
        character = findViewById(R.id.character);
        lines = findViewById(R.id.lines);
        sentences = findViewById(R.id.sentences);
        clearBtn = findViewById(R.id.clearBtn);

        copyBtn = findViewById(R.id.copyBtn);
        shareBtn = findViewById(R.id.shareBtn);
        shareWhatBtn = findViewById(R.id.shareWhatBtn);


        clearBtn.setOnClickListener(v -> {
            mainText.setText("");
        });

        copyBtn.setOnClickListener(v -> {
            String mainTxt = mainText.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(CountActivity.this, "Enter Text first to copied!");
            }else {
                Helper.copyText(CountActivity.this, mainTxt);
            }
        });

        shareWhatBtn.setOnClickListener(v -> {
            String mainTxt = mainText.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(CountActivity.this, "Enter Text first to share with WhatsApp!");
            }else {
                Helper.shareWithWhatsapp(CountActivity.this, mainTxt);
            }
        });

        shareBtn.setOnClickListener(v -> {
            String mainTxt = mainText.getText().toString().trim();
            if (mainTxt.equals("")){
                Helper.showAlert(CountActivity.this, "Enter Text first to share!");
            }else {
                Helper.share(CountActivity.this, mainTxt,0);
            }
        });

        mainText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ma = mainText.getText().toString();
                words = mainText.getText().toString().split("\\s+").length;
                characters = mainText.getText().toString().length();
                word.setText("Words: "+words);
                character.setText("Characters: "+characters);

                String sentence[] = ma.split("[!?.:]+");
                sentenceCount = sentence.length;
                sentences.setText("Sentences: "+sentenceCount);

                lines.setText("Lines: "+mainText.getLineCount());

            }
            @Override
            public void afterTextChanged(Editable s) {
                mainData = mainText.getText().toString();
                if (mainData.equals("")){
                    word.setText("Words: 0");
                    character.setText("Characters: 0");
                    lines.setText("Lines: 0");
                    sentences.setText("Sentences: 0");

                }

            }
        });
    }

}