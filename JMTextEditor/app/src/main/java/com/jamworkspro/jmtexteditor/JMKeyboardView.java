package com.jamworkspro.jamworksxg2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;
import android.view.View;
import java.util.List;

@SuppressLint("AppCompatCustomView")
public class JMKeyboardView extends KeyboardView
{
  JMEdit               m_JMEdit;
  Keyboard             m_Keyboard;
  List<Keyboard.Key>   m_Keys;
  public Keyboard.Key  m_keyCapsLocks;
  public boolean       m_CapsLockOn;

  public JMKeyboardView(Context context, AttributeSet attrs)
  {
    super(context, attrs);

    m_Keyboard = new Keyboard(context, R.xml.jmkeyboard);
    setKeyboard(m_Keyboard);

    if(isInEditMode())
    {
      return;
    }

    //Attach the keyboard to the view
    m_CapsLockOn = false;

    m_Keys = m_Keyboard.getKeys();

    int iSize = m_Keys.size();
    for (int i=0;i<iSize;i++)
    {
      Keyboard.Key key = m_Keys.get(i);
      if (key.label.equals("caps"))
        m_keyCapsLocks = key;
    }

    //Do not show the preview balloons
    setPreviewEnabled(false);

    //Install the key handler
    setOnKeyboardActionListener(m_OnKeyboardActionListener);
    setOnFocusChangeListener(new OnFocusChangeListener()
    {
      @Override
      public void onFocusChange(View v, boolean hasFocus)
      {
        int i=0;
      }
    });

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
    m_JMEdit = e;
  }

  public KeyboardView.OnKeyboardActionListener m_OnKeyboardActionListener = new KeyboardView.OnKeyboardActionListener()
  {
    @Override public void onKey(int primaryCode, int[] keyCodes)
    {
      //Toast.makeText(getApplicationContext(),"onKey", Toast.LENGTH_SHORT).show();
      if(m_JMEdit != null)
        m_JMEdit.onNewCharacter(primaryCode);
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
