package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class CalendarView
    extends GenericActivity<Calendar.PresenterToView, Calendar.ViewToPresenter, CalendarPresenter>
    implements Calendar.PresenterToView {

  private ImageButton menuImage;
  private TextView name, phone, mail, displayhour;
  private android.widget.CalendarView date;
  private SeekBar hour;
  private Button send;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_calendar);
    Log.d(TAG, "calling onCreate()");

    // Menú inferior
    ImageButton mapsMenuImage = (ImageButton) findViewById(R.id.m_maps);
    ImageButton chatMenuImage = (ImageButton) findViewById(R.id.m_chat);
    ImageButton webMenuImage = (ImageButton) findViewById(R.id.m_shop);
    menuImage = (ImageButton) findViewById(R.id.m_calendar);
    // Listeners del menú
    mapsMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onMapsButtonClicked();
      }
    });
    chatMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onChatButtonClicked();
      }
    });
    webMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onShopButtonClicked();
      }
    });

    // Pantalla Calendar
    name = (TextView) findViewById(R.id.name);
    phone = (TextView) findViewById(R.id.phone);
    mail = (TextView) findViewById(R.id.mail);
    date = (android.widget.CalendarView) findViewById(R.id.date);
    hour = (SeekBar) findViewById(R.id.hour);
    displayhour = (TextView) findViewById(R.id.display_hour);
    send = (Button) findViewById(R.id.send);
    send.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onSendButtonClicked();
      }
    });
  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(CalendarPresenter.class, this);
    menuImage.setImageResource(R.drawable.ic_calendar_icon_m);
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    Log.d(TAG, "calling onBackPressed()");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "calling onDestroy()");
  }


  ///////////////////////////////////////////////////////////////////////////////////
  // Presenter To View /////////////////////////////////////////////////////////////

  @Override
  public void finishScreen() {
    Log.d(TAG, "calling finishScreen()");
    finish();
  }

  @Override
  public void setDateView(String date) {
    // TODO: 8/4/18  Poner fecha en el calendario
  }

  @Override
  public void setHourBar(String hour) {
    // TODO: 8/4/18 Poner hora en la barra
  }

  @Override
  public void setHourText(String hour) {
    displayhour.setText("Hora: " + hour);
  }

  @Override
  public void setNameText(String name) {
    this.name.setText(name);
  }

  @Override
  public void setPhoneText(int phone) {
    this.phone.setText(Integer.toString(phone));
  }

  @Override
  public void setMailText(String mail) {
    this.mail.setText(mail);
  }

  @Override
  public void enableSendButton() {
    send.setEnabled(true);
  }

  @Override
  public void disableSendButon() {
    send.setEnabled(false);

  }

}
