package es.ulpgc.eite.clean.mvp.sample.utils;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import es.ulpgc.eite.clean.mvp.sample.R;

public class GeoUtils {

    public static String getNombreFromPosition(Context context,int position) {
        List<String> nombres = Arrays.asList(context.getResources().getStringArray(R.array.spinner));
        return nombres.get(position);
    }

    public static Double getLatitudFromPosition(int position) {
        Double result = 0.0;
        switch (position) {
            case 0:
                result = 28.47;
                break;
            case 1:
                result = 24.47;
                break;
            case 2:
                result = 22.47;
                break;
            case 3:
                result = 19.47;
                break;
        }
        return result;
    }

    public static Double getLongitudFromPosition(int position) {
        Double result = 0.0;
        switch (position) {
            case 0:
                result = -16.25;
                break;
            case 1:
                result = -12.25;
                break;
            case 2:
                result = -19.25;
                break;
            case 3:
                result = -15.25;
                break;
        }
        return result;
    }
}
