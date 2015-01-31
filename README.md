# CustomTimePicker
Beautiful TimePicker and DatePicker...
![Screenshot](https://github.com/SingleCycleKing/CustomTimePicker/blob/master/picture.png)
##使用方法
  1.将library导入，修改你的工程的gradle如下:

    dependencies {
      compile project(':library')
    }

  2.在xml中使用:
  ```xml
     <ui.DatePicker
        android:id="@+id/date_picker"
        android:background="#fff"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <ui.TimePicker
        android:id="@+id/time_picker"
        android:layout_marginTop="30dp"
        android:background="#fff"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />
  ```      
  3.在你的activity中:
  ```java
   datePicker.getCalendar();
   timePicker.getHour();
   timePicker.getMinute();
  ```
  
##说明
  Coder是枚大二狗，不足之处请多多指教～求star～
