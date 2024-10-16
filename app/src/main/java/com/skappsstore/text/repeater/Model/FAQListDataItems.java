package com.skappsstore.text.repeater.Model;

import com.skappsstore.text.repeater.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FAQListDataItems {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableDetailList = new HashMap<String, List<String>>();

        List<String> fa1 = new ArrayList<String>();
        fa1.add(("Because, according to the rule of the IPC binder android, you cannot generate more than 1MB data in application." +
                "To run the application smoothly on your device," +
                "we only allow you to generate text 5000 times at once.\n"));

        List<String> fa2 = new ArrayList<String>();
        fa2.add("Because, if you want to repeat long text then it will generate data more than 1MB. Try to add less limit for the repeated text. If possible enter a small number 1000 or 2000.\n");

        List<String> fa3 = new ArrayList<String>();
        fa3.add("Because if your device is not capable of handling a bunch of data then it will be given an ANR (Android Not Responding) error. So, better for you to add less limit for the repeated text.\n");

        List<String> fa4 = new ArrayList<String>();
        fa4.add("Because, this application generates a lot of data at runtimes. So, according to your device state we can try to handle the transaction situation," +
                "but we have not been sure about the state of the device. So, it will cause application crashes.\n");

        List<String> fa5 = new ArrayList<String>();
        fa5.add("Clipboard option is also part of the Android IPC binder transaction. Clipboard option is also part of the Android IPC binder transaction So, clipboard option copy your maximum data 1.5MB because here, according to the rule you can transfer 2MB data (it is only base of assumption and experiments results) also doing better performance but, we are assuming the data transaction make it less than 2MB for the better performance." +
                "It is only a base of assumption so, according to time and situation limits might be vary.\n");

        List<String> fa6 = new ArrayList<String>();
        fa6.add("If your repeated data might be more than sharing limit then it gives a simple message like please select clipboard option for the sharing.\n");

        List<String> fa7 = new ArrayList<String>();
        fa7.add("Because according to the rule of the IPC Binder android you can't transfer more than 1MB data from one application to another application, but not exactly 1MB is near about 1MB. So, if your repeated text is over 1MB, then it might cut the text from the original generated text and only share that much of the text which is cut." +
                "If your device is below Marshmallow then it shows only a warning message like something wrong but if your device is above the Marshmallow then it will create an application crash. It is only a base of assumption so, according to time and situation limits might be vary.\n");

        expandableDetailList.put("Why Text repeat only 5000 times?", fa1);
        expandableDetailList.put("Apps crashed even if entered 5000 times or less than that?", fa2);
        expandableDetailList.put("Why do devices hang sometimes?", fa3);
        expandableDetailList.put("Why do applications crash sometimes?", fa4);
        expandableDetailList.put("Why is the clipboard not copied all the data?", fa5);
        expandableDetailList.put("Click on the sharing option it gives message.", fa6);
        expandableDetailList.put("Why is all the repeated text cut and not shared with others?", fa7);
        return expandableDetailList;
    }
}
