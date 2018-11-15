package com.jamworkspro.jmtexteditor;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;

public class JMKeyboardView extends KeyboardView
{
  Keyboard mKeyboard;
  JMEdit mJMEdit;

  public JMKeyboardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
  {
    super(context, attrs,defStyleAttr,defStyleRes);
  }

  public JMKeyboardView(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
  }

  public JMKeyboardView(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    //Attach the keyboard to the view
    mKeyboard = new Keyboard(context, R.xml.jmkeyboard);
    setKeyboard(mKeyboard);

    //Do not show the preview balloons
    setPreviewEnabled(false);

    //Install the key handler
    setOnKeyboardActionListener(mOnKeyboardActionListener);
    showKeyboard();
  }

  public void showKeyboard()
  {
    setVisibility(View.VISIBLE);
    setEnabled(true);
  }

  public void hideKeyboard()
  {
    setVisibility(View.INVISIBLE);
    setEnabled(false);
  }

  public void SetEditText(JMEdit e)
  {
    mJMEdit = e;
  }

  public KeyboardView.OnKeyboardActionListener mOnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener()
  {
    @Override public void onKey(int primaryCode, int[] keyCodes)
    {
      //Toast.makeText(getApplicationContext(),"onKey", Toast.LENGTH_SHORT).show();
      mJMEdit.onNewCharacter(primaryCode);
    }

    @Override public void onPress(int arg0)
    {
      //Toast.makeText(getApplicationContext(),"onPress", Toast.LENGTH_SHORT).show();
    }

    @Override public void onRelease(int primaryCode)
    {
      //Toast.makeText(getApplicationContext(),"onRelease", Toast.LENGTH_SHORT).show();
    }

    @Override public void onText(CharSequence text)
    {
      //Toast.makeText(getApplicationContext(),"onText", Toast.LENGTH_SHORT).show();
    }

    @Override public void swipeDown()
    {
      //Toast.makeText(getApplicationContext(),"swipeDown", Toast.LENGTH_SHORT).show();
    }

    @Override public void swipeLeft()
    {
      //Toast.makeText(getApplicationContext(),"swipeLeft", Toast.LENGTH_SHORT).show();
    }

    @Override public void swipeRight()
    {
      //Toast.makeText(getApplicationContext(),"swipeRight", Toast.LENGTH_SHORT).show();
    }

    @Override public void swipeUp()
    {
      //Toast.makeText(getApplicationContext(),"swipeUp", Toast.LENGTH_SHORT).show();
    }
  };
}
