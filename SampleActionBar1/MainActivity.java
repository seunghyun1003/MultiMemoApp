package org.androidtown.sampleactionbar1;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    ActionBar abar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abar = this.getSupportActionBar();  // 액션바 객체 참조

        // 보여주고 싶다면 abar.show();
        // 감추고 싶다면 abar.hide();

        abar.setSubtitle("옵션바 살펴보기");  // 타이틀의 부제목 설정
        textView = (TextView) findViewById(R.id.textView);
    }

    // 액션바의 아이콘을 바꿈
    public void onButton1Clicked(View v) {
        abar.setLogo(R.drawable.home);
        abar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME|ActionBar.DISPLAY_USE_LOGO);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_refresh:  // 새로고침 메뉴 선택
                textView.setText("새로고침 메뉴를 선택했습니다.");
                return true;

            case R.id.menu_search:  // 검색 메뉴 선택
                textView.setText("검색 메뉴를 선택했습니다.");
                return true;

            case R.id.menu_settings:  // 설정 메뉴 선택
                textView.setText("설정 메뉴를 선택했습니다.");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

