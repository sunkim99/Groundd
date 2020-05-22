package com.example.ground;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MultiplicationView {
    private final String logTag = "MultiplicationView";

    private Context context;
    public final void getContext(Context context) {
        this.context = context;
    }

    private View getPosition;
    public final void getPlace(View where) {
        this.getPosition = where;
    }

    private TextView getTextView;
    public final TextView popupTextView() { return this.getTextView; }

    private TextView getCountTextView;
    public final TextView popupCountTextView() { return this.getCountTextView; }

    private EditText getEditText;
    public final EditText popupEdittext() {
        return this.getEditText;
    }

    private Button getExistButton;
    public final Button popupExistButton() {
        return this.getExistButton;
    }

    private Button getNextButton;
    public final Button popupNextButton() {
        return this.getNextButton;
    }

    private PopupWindow popupWindow = null;
    public final PopupWindow showPopupView() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.popup_multiplication, (ViewGroup) null);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


        try {
            popupWindow.setFocusable(true);
            popupWindow.setBackgroundDrawable((Drawable)(new ColorDrawable(0)));
            popupWindow.showAtLocation(this.getPosition, Gravity.CENTER, 0, 0);

            if (getTextView == null) getTextView = (TextView)view.findViewById(R.id.popup_multiplication_question);
            if (getEditText == null) getEditText = (EditText)view.findViewById(R.id.popup_multiplication_answer);
            if (getNextButton == null) getNextButton = (Button)view.findViewById(R.id.popup_multiplication_next);
            if (getExistButton == null) getExistButton = (Button)view.findViewById(R.id.popup_multiplication_exist);
            if (getCountTextView == null) getCountTextView = (TextView)view.findViewById(R.id.popup_multiplication_count);


        }catch (Exception e){
            Log.d(logTag,"Exception ");
        }finally {
            return popupWindow;
        }
    }

    public final void dismiss() {
        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
            Log.d(logTag, "팝업뷰가 사라짐");
        }
    }
}
