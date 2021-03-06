package es.ulpgc.eite.clean.mvp.sample.detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import es.ulpgc.eite.clean.mvp.GenericActivity;
import es.ulpgc.eite.clean.mvp.sample.R;
import es.ulpgc.eite.clean.mvp.sample.data.ShopItem;

public class DetailChatView
    extends GenericActivity<DetailChat.PresenterToView, DetailChat.ViewToPresenter, DetailChatPresenter>
    implements DetailChat.PresenterToView {


  private ImageButton menuImage;
  private ShopItem item;
  private Toolbar toolbar;
  private CollapsingToolbarLayout toolbarLayout;
  private AppBarLayout appbarLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_item_detail);
    Log.d(TAG, "calling onCreate()");

    // Menú inferior
    ImageButton mapsMenuImage = (ImageButton) findViewById(R.id.m_maps);
    ImageButton calendarMenuImage = (ImageButton) findViewById(R.id.m_calendar);
    ImageButton webMenuImage = (ImageButton) findViewById(R.id.m_shop);
    menuImage = (ImageButton) findViewById(R.id.m_chat);

    // Listeners del menú
    mapsMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getPresenter().onMapsButtonClicked();
      }
    });

    webMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        getPresenter().onShopButtonClicked();
      }
    });

    calendarMenuImage.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
       getPresenter().onCalendarButtonClicked();
      }
    });

    toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
    setSupportActionBar(toolbar);

    // Show the Up button in the action bar.
    ActionBar actionbar = getSupportActionBar();
    if (actionbar != null) {
      actionbar.setDisplayHomeAsUpEnabled(true);
    }

    toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
    appbarLayout = (AppBarLayout) findViewById(R.id.appbar_layout);
    appbarLayout.setExpanded(true);

  }

  /**
   * Method that initialized MVP objects
   * {@link super#onResume(Class, Object)} should always be called
   */
  @SuppressLint("MissingSuperCall")
  @Override
  protected void onResume() {
    super.onResume(DetailChatPresenter.class, this);
    menuImage.setImageResource(R.drawable.ic_chat_icon_m);

    item = getPresenter().getItem();
    if (toolbarLayout != null && item != null) {
      toolbarLayout.setTitle(item.getContent());
    }
    // Show the dummy content as text in a TextView.
    if (item != null) {
      ((TextView) findViewById(R.id.item_detail)).setText(item.getDetails());
    }
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
  public void hideToolbar() {
    toolbar.setVisibility(View.GONE);
    appbarLayout.setExpanded(false);
  }

}
