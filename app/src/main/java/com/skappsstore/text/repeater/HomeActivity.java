package com.skappsstore.text.repeater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.skappsstore.text.repeater.Adapter.HomeServiceAdapter;
import com.skappsstore.text.repeater.Model.HomeServiceModel;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView homeRecycler;
    private ArrayList<HomeServiceModel> homeServiceModels;

    private String[] servicesTitle;
//    private static final int RC_APP_UPDATE = 100;
    private InAppUpdate inAppUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        inAppUpdate = new InAppUpdate(HomeActivity.this);
        inAppUpdate.checkForAppUpdate();


        homeRecycler =findViewById(R.id.homeRecycler);
        servicesTitle = getResources().getStringArray(R.array.servicesTitle);
        homeServiceModels = new ArrayList<>();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeActivity.this, 1);
        homeRecycler.setLayoutManager(gridLayoutManager);

        for (int i=0; i<servicesTitle.length; i++){

            String title = servicesTitle[i];
            int pic = Helper.servicesImage[i];

            homeServiceModels.add(new HomeServiceModel(title, pic));

        }

        HomeServiceAdapter homeCateAdapter = new HomeServiceAdapter(HomeActivity.this , homeServiceModels);

        homeRecycler.setAdapter(homeCateAdapter);

//        callUpdate();


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        inAppUpdate.onActivityResult(requestCode, resultCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        inAppUpdate.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        inAppUpdate.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.tollbar_menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(HomeActivity.this, HelperActivity.class);
        Bundle bundle = new Bundle();
        switch (item.getItemId()){
            case
                    R.id.faqBtnMenu:
                    bundle.putString("type","faq");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

            case
                    R.id.rateBtnMenu:
                    Helper.openApps(HomeActivity.this);
                    break;

            case
                    R.id.aboutBtnMenu:
                    bundle.putString("type","about");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            case
                    R.id.termsBtnMenu:
                    bundle.putString("type","terms");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;
            case
                    R.id.privacyBtnMenu:
                    bundle.putString("type","privacy");
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

            case
                    R.id.shareBtnMenu:
                    Helper.share(HomeActivity.this,getResources().getString(R.string.shareApps),1);
                    break;
        }

        return true;
    }


    public void checkUpdate(View view) {
        Helper.openApps(HomeActivity.this);
    }

}