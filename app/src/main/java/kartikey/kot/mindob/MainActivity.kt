package kartikey.kot.mindob

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.sql.Time
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var selectedDate : TextView? = null

    private var TimeInSec : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDate : Button = findViewById(R.id.buttonDatePic)
        selectedDate= findViewById(R.id.Date)
        TimeInSec= findViewById(R.id.Time)
        buttonDate.setOnClickListener{
            clickDatePicker()
        }
    }

    fun clickDatePicker(){

        val myCalender = Calendar.getInstance()
        val year= myCalender.get(Calendar.YEAR)
        val month= myCalender.get(Calendar.MONTH)


        val day= myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            { view, selectedyear, selectedmonth, selecteddayOfMonth ->
                Toast.makeText(this, "Year was $selectedyear" +
                        ",month was ${selectedmonth+1}" +
                        ",day of the month $selecteddayOfMonth", Toast.LENGTH_LONG).show()

                val selectDate= "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"

                selectedDate?.text= selectDate

                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate= sdf.parse(selectDate)

                val selectedDateInSec= theDate.time / 1000

                val selectedCurrDate= sdf.parse(sdf.format(System.currentTimeMillis()))

                val selectedCurrDateInSec= selectedCurrDate.time / 1000

                val differenceInMinutes= selectedCurrDateInSec- selectedDateInSec

                TimeInSec?.text= differenceInMinutes.toString()// putting question mark for type safety

            },
            year,
            month,
            day
            ).show()

//        Toast.makeText(this, "button pressed", Toast.LENGTH_LONG).show()
    }
}