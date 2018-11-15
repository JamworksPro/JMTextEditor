package com.jamworkspro.jmtexteditor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    JMEdit jmEdit = findViewById(R.id.JMEdit);
    JMKeyboardView jmKeyboardView = findViewById(R.id.JMKeyboardView);
    jmKeyboardView.SetEditText(jmEdit);
  }
}
