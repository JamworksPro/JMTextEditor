# JMEditText
Android Custom Keyboard and Simple Text Editor

Purpose
The purpose of this project is to provide a working example a custom keyboard implementation along with a custom EditText object to connect to the keyboard.  The user may take the JMEdit.java and the JMKeyboardView.java classes and drop them into an Android project and then find them available as visual objects in Android Studio toolbox pallet.

Motivation
The reason I wrote this custom text editor and keyboard combination is because I found the existing Android solution was inadequate for my Algebraic Expression Based Calculator.  The existing Android solution is to implement an EditText object which by default invokes the built in soft keyboard for editing the text.  The input requirements for my calculator represent a significant subset of options provided by the built in soft keyboard.  So I need a custom keyboard that is customized to provide a set of keys for mathematical notation as well as navigation of the cursor in the EditText object.

This is easy enough to do, however invoking the custom keyboard when the EditText object receives focus is a problem because Androids' EditText Object will ALWAYS invoke the built in soft keyboard.  There is one way and only one way I have found to prevent this, and this is to use its "setRawInputType" method to set the its input type to NULL.  (setRawInputType(InputType.TYPE_NULL);)

About the JMEdit Custom Class
The setRawInputType method is defined in the Android TextView object and thus is inherited by the EditText object.  There are other attributes in the Android TextView object relating to input methods.  I suppose that these input methods and properties can be used to derive custom editor/keyboard objects.  I believe that this is the purpose of the EditText object which is derived from the TextView object.

The Android EditText object is also designed to respond gestures.  This allows the user to position the cursor and select text using gestures.  Androids' built is soft keyboard is designed to recognize these actions and and work with the EditText to place keyboard input accordingly.  Consequently my custom Soft Keyboard could likewise track and recognize these gestures and respond accordingly however this  is unnecessary since the custom keyboard provides all the editor navigation required.  Also, since the built in soft keyboard has been disabled, the EditText loses its ability to provide a blinking cursor.  The only way show a cursor in the custom EditText is to use its text selection methods.

If you want all the bells and whistles of a full blown text editor (select text, copy, paste, delete, position cursor with gestures, etc..) with a custom soft keyboard, then it appears that you need to begin with the Android TextView object and utilize its input methods and properties to hide the built in keyboard and respond to your custom keyboard.  This appears to be quite a complex task.

To turn off the ability to select text in Androids' TextView with a gesture, use setTextIsSelectable(false) method.  To ensure that the built in blinking cursor is never displayed, use the setCursorVisible(false) method.

Once the EditTexts' input type is set to NULL it is the applications responsibility to implement a functional text editor.  The EditText object in this project is the JMEdit class which is derived from the Android EditText object.  The navigation functionality is based on the design specified in the editorV1.pdf document.

About the JMKeyboardView Custom Class
The JMKeyboardView is derived from the Android KeyboardView class.  The keys that compose the keyboard are defined in an XML file located in the apps res.XML source folder.  The setKeyboard method in the KeyboardView class is used to attach the XML defined keyboard to the KeyboardView. 

The custom JMKeyboardView class provides the SetEditText method to attach an EditText object that responds to the keystrokes.
An OnKeyboardActionListener interface must be implemented in order to trap the keystrokes.  This interface is where the notification of new characters are sent to its attached EditText object.
