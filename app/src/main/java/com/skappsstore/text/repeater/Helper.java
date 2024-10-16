package com.skappsstore.text.repeater;
import static android.content.Context.CLIPBOARD_SERVICE;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Helper {

    public static void copyText(Context context, String text){
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("text", text.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Text Copied Successfully", Toast.LENGTH_SHORT).show();
    }


    public static void shareWithWhatsapp(Context context,String message){

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.whatsapp");

        intent.putExtra(Intent.EXTRA_TEXT, message.toString());


        if (intent.resolveActivity(context.getPackageManager()) == null) {
            Toast.makeText(context,"Please install whatsapp first", Toast.LENGTH_SHORT).show();
            return;
        }
        context.startActivity(intent);
    }

    public static void share(Context context, String text, int type){

        String ty = null;
        if (type==0){
            ty ="Message";
        }else{
            ty = context.getString(R.string.app_name);
            text = text + " https://play.google.com/store/apps/details?id="+ context.getPackageName().toString();
        }

        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text);
        context.startActivity(Intent.createChooser(intent, "Share "+ty));
    }

    public static void showAlert(Context context , String text){
        SweetAlertDialog sweetAlertDialog2 = new SweetAlertDialog(context ,SweetAlertDialog.ERROR_TYPE);
        sweetAlertDialog2 = sweetAlertDialog2.setTitleText("Something went wrong");
        sweetAlertDialog2 = sweetAlertDialog2.setContentText(text);
        sweetAlertDialog2.setCancelable(false);
        sweetAlertDialog2.setConfirmText("Ok");
        sweetAlertDialog2.setConfirmClickListener(sweetAlertDialog -> {
            sweetAlertDialog.dismiss();

        });
        sweetAlertDialog2.show();
    }

    public static void showInstruction(Context context, String text){
        Dialog noDialog;
        noDialog = new Dialog(context);
        noDialog.setContentView(R.layout.instruction_layout);
        noDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnCloseno = noDialog.findViewById(R.id.btnClose);
        TextView txtInstruction = noDialog.findViewById(R.id.txtInstruction);

        txtInstruction.setText(text);
        btnCloseno.setOnClickListener(view -> {
            noDialog.dismiss();
        });

        noDialog.show();
    }

    public static int servicesImage[] = {
            R.drawable.text_repeat,
            R.drawable.blank_text,
            R.drawable.word_counter,
            R.drawable.join_text,

    };

    public static void openService(Context context, int to){

        Intent intent = null;
        if (to==0){
            intent = new Intent(context, RepeatedActivity.class);
        }else if (to==1){
            intent = new Intent(context, BlankActivity.class);
        }else if (to==2){
            intent = new Intent(context, CountActivity.class);
        }else if (to==3){
            intent = new Intent(context, AddTextActivity.class);
        }
        context.startActivity(intent);

    }

    public static void openApps(Context context){
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    public static boolean connectioncheck(Context mcontex) {

        ConnectivityManager connectivityManager = (ConnectivityManager) mcontex.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkinfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkinfo != null && activeNetworkinfo.isConnected();
    }


}
