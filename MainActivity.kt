package com.example.a4_4

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat

class MainActivity : AppCompatActivity() {

    private lateinit var swStart: SwitchCompat
    private lateinit var rgVersions: RadioGroup
    private lateinit var rbOreo: RadioButton
    private lateinit var rbPie: RadioButton
    private lateinit var rbQ: RadioButton
    private lateinit var ivAndroid: ImageView
    private lateinit var btnExit: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bindViews()
        initUi()
        setListeners()
    }

    private fun bindViews() {
        swStart = findViewById(R.id.swStart)
        rgVersions = findViewById(R.id.rgVersions)
        rbOreo = findViewById(R.id.rbOreo)
        rbPie = findViewById(R.id.rbPie)
        rbQ = findViewById(R.id.rbQ)
        ivAndroid = findViewById(R.id.ivAndroid)
        btnExit = findViewById(R.id.btnExit)
        btnReset = findViewById(R.id.btnReset)
    }

    private fun initUi() {
        setChoiceEnabled(false)      // 시작 전엔 비활성화
        ivAndroid.visibility = View.GONE
        rgVersions.clearCheck()
        swStart.isChecked = false
    }

    private fun setListeners() {

        swStart.setOnCheckedChangeListener { _, isChecked ->
            setChoiceEnabled(isChecked)
            if (!isChecked) reset()
        }


        rgVersions.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rbOreo -> {
                    ivAndroid.setImageResource(R.drawable.oreo)
                    ivAndroid.visibility = View.VISIBLE
                }
                R.id.rbPie -> {
                    ivAndroid.setImageResource(R.drawable.pie)
                    ivAndroid.visibility = View.VISIBLE
                }
                R.id.rbQ -> {
                    ivAndroid.setImageResource(R.drawable.q10)
                    ivAndroid.visibility = View.VISIBLE
                }
                -1 -> ivAndroid.visibility = View.GONE
            }
        }


        btnExit.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity()
            } else {
                finish()
            }
        }


        btnReset.setOnClickListener { reset() }
    }

    private fun setChoiceEnabled(enabled: Boolean) {
        rbOreo.isEnabled = enabled
        rbPie.isEnabled = enabled
        rbQ.isEnabled = enabled
    }

    private fun reset() {
        swStart.isChecked = false
        setChoiceEnabled(false)
        rgVersions.clearCheck()
        ivAndroid.visibility = View.GONE
    }
}
