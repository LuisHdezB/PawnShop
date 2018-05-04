package es.ulpgc.eite.clean.mvp.sample.maps;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import es.ulpgc.eite.clean.mvp.sample.R;

public class CustomMapMarker implements GoogleMap.InfoWindowAdapter{

    private Activity activity;
    private MapWrapperLayout mapWrapperLayout;

    public CustomMapMarker(Activity activity, MapWrapperLayout mapWrapperLayout) {
        this.activity = activity;
        this.mapWrapperLayout = mapWrapperLayout;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        View view = activity.getLayoutInflater().inflate(R.layout.custom_marker_layout, null);

        Button button = (Button) view.findViewById(R.id.cml_favorite);

        Resources resources = activity.getResources();
        Drawable normalBg = resources.getDrawable(R.drawable.bg_normal);
        Drawable pressedBg = resources.getDrawable(R.drawable.bg_normal);

        OnInfoWindowElemTouchListener touchListener = new OnInfoWindowElemTouchListener(button, normalBg, pressedBg) {
            @Override
            protected void onClickConfirmed(View v, Marker marker) {
                Toast.makeText(activity, "Hola!", Toast.LENGTH_SHORT).show();
            }
        };

        touchListener.setMarker(marker);
        button.setOnTouchListener(touchListener);

        mapWrapperLayout.setMarkerWithInfoWindow(marker, view);

        return view;
    }
}
