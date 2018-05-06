package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;

public class CalendarView
    extends GenericActivity<Calendar.PresenterToView, Calendar.ViewToPresenter, CalendarPresenter>
    implements Calendar.PresenterToView {

  private ImageButton menuImage;
  private EditText name, phone, mail, products;
  private android.widget.CalendarView date;
  private Spinner hours;
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
    name = (EditText) findViewById(R.id.name);
    phone = (EditText) findViewById(R.id.phone);
    mail = (EditText) findViewById(R.id.mail);
    date = (android.widget.CalendarView) findViewById(R.id.date);
    hours = (Spinner) findViewById(R.id.hour);
    products = (EditText) findViewById(R.id.products);
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
    java.util.Calendar cal = java.util.Calendar.getInstance();
    this.date.setMinDate(cal.getTimeInMillis());
  }

  @Override
  public void setHours(ArrayList<String> hours) {
    this.hours.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hours));
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

  @Override
  public String getNameInputText() {
    return name.getText().toString();
  }

  @Override
  public String getMailInputText() {
    return mail.getText().toString();
  }

  @Override
  public int getInputPhoneText() {
    return Integer.parseInt(phone.getText().toString());
  }

  @Override
  public String getInputProductsText() {
    return products.getText().toString();
  }

}
