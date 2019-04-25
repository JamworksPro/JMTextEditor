package com.jamworkspro.jamworksxg2;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.View;

public class JMEdit extends android.support.v7.widget.AppCompatEditText
{
  JMKeyboardView       m_JMKeyboardView;

  public int           m_iCursorBegin;
  public int           m_iCursorEnd;
  public String        m_strLeft;
  public String        m_strRight;
  public String        m_strNewCharacter;
  JMEdit               m_this;

  //Constructors
  public JMEdit(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    setText(" ");
    int len = getText().length();
    setSelection(0, len);
    setRawInputType(InputType.TYPE_NULL);
    setTextIsSelectable(false);
    setBackgroundColor(0xff00ff00);
    setCursorVisible(false);
    m_JMKeyboardView = null;
    m_this = this;

    setOnFocusChangeListener(new OnFocusChangeListener()
    {
      @Override
      public void onFocusChange(View v, boolean hasFocus)
      {
        if(m_JMKeyboardView == null)return;

        m_JMKeyboardView.SetEditText(m_this);
        if(hasFocus)
          m_JMKeyboardView.showKeyboard();
        else
          m_JMKeyboardView.hideKeyboard();
      }
    });
  }

  public JMEdit(Context context)
  {
    super(context);
  }

  public void SetKeyboardView(JMKeyboardView e)
  {
    m_JMKeyboardView = e;
  }

  //Edit Function
  private void AddCharacter()
  {
    String strNewText = m_strLeft + m_strNewCharacter + m_strRight;
    {
      m_iCursorBegin++;
      m_iCursorEnd++;
      setText(strNewText);
      setSelection(m_iCursorBegin, m_iCursorEnd);
    }
  }

  private void DeleteCharacter()
  {
    if(m_strRight.length() > 1)
    {
      String modStringRight = m_strRight.substring(1, m_strRight.length());
      String strNewString = m_strLeft + modStringRight;
      setText(strNewString);
      setSelection(m_iCursorBegin, m_iCursorEnd);
    }
  }

  private void CAPS()
  {
    if(m_JMKeyboardView == null)
      return;

    boolean bShifted = m_JMKeyboardView.isShifted();
    m_JMKeyboardView.setShifted(!bShifted);
    m_JMKeyboardView.m_CapsLockOn = !bShifted;

    if(m_JMKeyboardView.m_CapsLockOn)
      m_JMKeyboardView.m_keyCapsLocks.label = "CAPS";
    else
      m_JMKeyboardView.m_keyCapsLocks.label = "caps";
  }

  private void Home()
  {
    if(m_iCursorBegin > 0)
    {
      int st = 0;
      int en = 1;
      m_iCursorBegin = st;
      m_iCursorEnd = en;
      setSelection(m_iCursorBegin, m_iCursorEnd);
    }
  }

  private void End()
  {
    {
      int len =  getText().length();
      int st = len - 1;
      int en =  len;
      m_iCursorBegin = st;
      m_iCursorEnd = en;
      setSelection(m_iCursorBegin, m_iCursorEnd);
    }
  }

  private void LeftArrow()
  {
    if(m_iCursorBegin > 0)
    {
      int st = getSelectionStart();
      int en = getSelectionEnd();
      m_iCursorBegin = st - 1;
      m_iCursorEnd = en - 1;
      setSelection(m_iCursorBegin, m_iCursorEnd);
    }
  }

  private void RightArrow()
  {
    if(m_iCursorEnd < getText().length())
    {
      int st = getSelectionStart();
      int en = getSelectionEnd();
      m_iCursorBegin = st + 1;
      m_iCursorEnd = en + 1;
      setSelection(m_iCursorBegin, m_iCursorEnd);
    }
  }

  private void Clear()
  {
    setText(" ");
    m_iCursorBegin = 0;
    m_iCursorEnd = 1;
    setSelection(m_iCursorBegin, m_iCursorEnd);
  }

  public void onNewCharacter(int primaryCode)
  {
    //store the new character
    m_strNewCharacter = Character.toString((char)primaryCode);

    //note the contents of the original string
    String strCurrentText = getText().toString();

    //note the cursor position.  a zero length cursor indicates nothing selected
    m_iCursorBegin = getSelectionStart();
    m_iCursorEnd = getSelectionEnd();

    //Split the current string
    m_strLeft = strCurrentText.substring(0, m_iCursorBegin);//include the selection on the right
    m_strRight = strCurrentText.substring(m_iCursorBegin, strCurrentText.length());

    if(primaryCode >= 32 && primaryCode <= 126)//process a printable character
    {
      if(m_JMKeyboardView.m_CapsLockOn && primaryCode >= 97 && primaryCode <= 122)
        m_strNewCharacter = Character.toString((char)(primaryCode-32));

      AddCharacter();
    }

    if(primaryCode == 127)//delete
      DeleteCharacter();

    if(primaryCode == -1)//Home
      Home();

    if(primaryCode == -2)//left arrow
      LeftArrow();

    if(primaryCode == -3)//right arrow
      RightArrow();

    if(primaryCode == -4)//end
      End();

    if(primaryCode == -5)//backspace
    {
      int[] i={0};
      onNewCharacter(-2);
      onNewCharacter(127);
    }

    if(primaryCode == -6)//clear
      Clear();

    if(primaryCode == -7)//caps lock
      CAPS();
  }
}
