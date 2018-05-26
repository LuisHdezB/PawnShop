package es.ulpgc.eite.clean.mvp.sample.calendar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

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
    date.setOnDateChangeListener(new android.widget.CalendarView.OnDateChangeListener(){
      @Override
      public void onSelectedDayChange(@NonNull android.widget.CalendarView view, int year, int month, int dayOfMonth) {
        month = month + 1;
        Log.d(TAG, "onSelectedDayChange: date: " + year + "-" + month + "-" + dayOfMonth);
        getPresenter().changeDate(year + "-" + month + "-" + dayOfMonth);
      }
    });
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
    cal.add(java.util.Calendar.DATE, 1);
    long dateOld = cal.getTimeInMillis();
    this.date.setMinDate(dateOld);

    if (date != null) {
      long dateNew = convertToCalendar(date).getTimeInMillis();
      if (dateNew > dateOld) {
        Log.d(TAG, "setDateView: dateNew: " + date);
        this.date.setDate(dateNew, true, true);
      } else {
        Log.d(TAG, "setDateView: dateOld: " + date);
        this.date.setDate(dateOld, true, true);
      }
    } else {
      Log.d(TAG, "setDateView: dateOld si date null:");
      this.date.setDate(dateOld, true, true);
    }
  }

  @Override
  public void setHours(ArrayList<String> hours) {
    this.hours.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, hours));
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

  @Override
  public void setProductsText(String products) {
    this.products.setText(products);
  }

  @Override
  public int getHour() {
    return hours.getSelectedItemPosition();
  }

  @Override
  public void setHourSelected(int idHour) {
    this.hours.setSelection(idHour,true);
  }

  /**
   * Método convertToCalendar para convertir una fecha de String a Calendar.
   *
   * @param date Fecha en formato String
   * @return Fecha en formato Calendar
   */
  private java.util.Calendar convertToCalendar(String date) {
    java.util.Calendar cal = java.util.Calendar.getInstance();
    String parts[] = date.split("-");
    Log.d(TAG, "convertToCalendar: date:" + date);
    int day = Integer.parseInt(parts[2]);
    int month = Integer.parseInt(parts[1]) - 1;
    int year = Integer.parseInt(parts[0]);
    cal.set(year, month, day);
    return cal;
  }

  /**
   * Método convertToString para convertir una fecha de Calendar a String.
   *
   * @param cal Fecha en formato Calendar
   * @return Fecha en formato String
   */
  private String convertToString(java.util.Calendar cal) {
    int year, month, day;
    year = cal.get(java.util.Calendar.YEAR);
    month = cal.get(java.util.Calendar.MONTH); // Los meses van de 0-11
    day = cal.get(java.util.Calendar.DAY_OF_MONTH) + 1;
    Log.d(TAG, "convertToString: date:" + year + "-" + month + "-" + day);
    return year + "-" + month + "-" + day;
  }

}
