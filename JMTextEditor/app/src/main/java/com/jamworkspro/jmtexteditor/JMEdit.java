package com.jamworkspro.jmtexteditor;

import android.content.Context;
import android.text.InputType;
import android.util.AttributeSet;
import android.widget.EditText;

public class JMEdit extends EditText
{
  public int           m_iCursorBegin;
  public int           m_iCursorEnd;
  public String        m_strLeft;
  public String        m_strRight;
  public String        m_strNewCharacter;

  //Constructors
  public JMEdit(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
  {
    super(context, attrs, defStyleAttr);
    setText(" ");
    int len = getText().length();
    setSelection(0, len);
    setRawInputType(InputType.TYPE_NULL);
    setTextIsSelectable(false);
    setBackgroundColor(0xffff0000);
    setCursorVisible(false);
  }

  public JMEdit(Context context, AttributeSet attrs, int defStyleAttr)
  {
    super(context, attrs, defStyleAttr);
    setText(" ");
    int len = getText().length();
    setSelection(0, len);
    setRawInputType(InputType.TYPE_NULL);
    setTextIsSelectable(false);
    setBackgroundColor(0xff0000ff);
    setCursorVisible(false);
  }

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

    if(primaryCode>=32 && primaryCode <= 126)//process a printable character
      AddCharacter();

    if(primaryCode == -2)//left arrow
      LeftArrow();

    if(primaryCode == -3)//right arrow
      RightArrow();

    if(primaryCode == 127)//delete
      DeleteCharacter();

    if(primaryCode == -1)//backspace
    {
      int[] i={0};
      onNewCharacter(-2);
      onNewCharacter(127);
    }
  }
}
