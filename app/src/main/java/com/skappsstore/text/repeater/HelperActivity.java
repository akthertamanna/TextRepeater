package com.skappsstore.text.repeater;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.skappsstore.text.repeater.Adapter.FaqAdapter;
import com.skappsstore.text.repeater.Model.FAQListDataItems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HelperActivity extends AppCompatActivity {

    private String type = null;

    ExpandableListView faqListView;
    FaqAdapter faqAdapter;

    List<String> expandableTitleList;
    HashMap<String, List<String>> expandableDetailList;

    private WebView webviewHelpID;
    private String url = "file:///android_asset/";

    private LinearLayout faqSection ;
    private NestedScrollView browserID;
    ActionBar s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helper);



        s = getSupportActionBar();
//       s.setTitle("FAQ");


        faqListView = findViewById(R.id.faqListView);
        webviewHelpID = findViewById(R.id.webviewHelpID);
        faqSection = findViewById(R.id.faqSection);
        browserID = findViewById(R.id.browserID);



        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            type = bundle.getString("type", "none").toString();
            getView(type);
        }

    }

    private void getView(String type) {
        if (type.equals("faq")){

            s.setTitle("FAQ");
            expandableDetailList = FAQListDataItems.getData();
            expandableTitleList = new ArrayList<String>(expandableDetailList.keySet());
            faqAdapter = new FaqAdapter(this, expandableTitleList, expandableDetailList);
            faqListView.setAdapter(faqAdapter);

            faqSection.setVisibility(View.VISIBLE);
            browserID.setVisibility(View.GONE);

        }else{

            faqSection.setVisibility(View.GONE);
            browserID.setVisibility(View.VISIBLE);

            if (type.equals("terms")) {
                s.setTitle("Terms & Conditions");
                webviewHelpID.loadUrl(url + "terms.html");

            } else if (type.equals("privacy")) {
                s.setTitle("Privacy & Policy");
                webviewHelpID.loadUrl(url + "privacy.html");

            } else if (type.equals("about")) {
                s.setTitle("About Us");
                webviewHelpID.loadUrl(url + "about.html");
            }
            webviewHelpID.getSettings().setJavaScriptEnabled(true);
            webviewHelpID.setWebViewClient(new WebViewClient());


        }








    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}