package org.androidtown.bitmapwidget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatButton;

public class BitmapButton extends AppCompatButton {
	//아이콘 리소스 정의
	int iconNormal = R.drawable.bitmap_button_normal;
	int iconClicked = R.drawable.bitmap_button_clicked;

	//아이콘 상태 정의
	int iconStatus = STATUS_NORMAL;
	public static int STATUS_NORMAL = 0;
	public static int STATUS_CLICKED = 1;

	//소스 코드에서 객체를 생성했을 때 호출되는 생성자
	public BitmapButton(Context context) {
		super(context);
		init();
	}

	//XML에 추가된 버튼이 인플레이션될 때 호출되는 생성자
	public BitmapButton(Context context, AttributeSet atts) {
		super(context, atts);
		init();
	}

	public void init() {
		setBackgroundResource(iconNormal);

		int defaultTextColor = Color.WHITE;
		float defaultTextSize = getResources().getDimension(R.dimen.text_size);
		Typeface defaultTypeface = Typeface.DEFAULT_BOLD;

		setTextColor(defaultTextColor);
		setTextSize(defaultTextSize);
		setTypeface(defaultTypeface);
	}

	//아이콘 리소스 설정
	public void setIcon(int iconNormal, int iconClicked) {
		this.iconNormal = iconNormal;
		this.iconClicked = iconClicked;
	}


	//터치 이벤트 처리
	public boolean onTouchEvent(MotionEvent event) {
		super.onTouchEvent(event);

		int action = event.getAction();

		switch (action) {
			case MotionEvent.ACTION_DOWN:
				setBackgroundResource(R.drawable.bitmap_button_clicked);
				iconStatus = STATUS_CLICKED;
				break;

			case MotionEvent.ACTION_OUTSIDE:
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				setBackgroundResource(R.drawable.bitmap_button_normal);
				iconStatus = STATUS_NORMAL;
				break;
		}

		// 다시 그리기
		invalidate();
		return true;
	}

}
