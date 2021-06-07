package com.kiranbahalaskar.whatsappintent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.rilixtech.widget.countrycodepicker.CountryCodePicker
import java.lang.Exception
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {

    private lateinit var countryCode : CountryCodePicker
    private lateinit var etPhoneNumber : EditText
    private lateinit var btnWhatsapp : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countryCode = findViewById(R.id.countryCode)
        etPhoneNumber = findViewById(R.id.etPhoneNumber)
        btnWhatsapp = findViewById(R.id.btnWhatsapp)

        val code = countryCode.selectedCountryCodeWithPlus.toString()

        btnWhatsapp.setOnClickListener {
            whatsApp(code, etPhoneNumber.text.toString().trim())
        }


    }

    private fun whatsApp(code: String, phoneNumber: String) {

        try {

            val packageManager = this.packageManager
            val i = Intent(Intent.ACTION_VIEW)
            val url = "https://api.whatsapp.com/send?phone=" +"$code $phoneNumber" + "&text=" +
                    URLEncoder.encode("Hello I am KB Coder")
            i.setPackage("com.whatsapp")
            i.data = Uri.parse(url)
            if (i.resolveActivity(packageManager) != null){
                startActivity(i)
            }else{
                Toast.makeText(this, "Please Install Whats App", Toast.LENGTH_SHORT).show()
            }

        }catch (e: Exception){
            Toast.makeText(this, ""+e.stackTrace, Toast.LENGTH_SHORT).show()
        }

    }
}