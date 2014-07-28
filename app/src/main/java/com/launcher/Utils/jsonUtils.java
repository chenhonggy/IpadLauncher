package com.launcher.Utils;

import android.content.Context;

import com.launcher.ipadlauncher.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by chen on 14-7-19.
 */
public class jsonUtils {
    public static String getJson(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    context.getResources().openRawResource(R.raw.app_json)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
                }
            } catch (IOException e) {
            e.printStackTrace();
            }
        return stringBuilder.toString();
    }
}
