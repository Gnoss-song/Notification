package com.example.practicenotifications

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.practicenotifications.DynamicLinkUtils.onDynamicLinkClick
import com.example.practicenotifications.SchemeActivity.Companion.PARAM_ID2
import com.example.practicenotifications.databinding.ActivityDynamicLinkBinding


class DynamicLinkActivity : AppCompatActivity() {
    private lateinit var binding : ActivityDynamicLinkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDynamicLinkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addClickListener()
    }

    private fun addClickListener(){
        binding.btnShareMain.setOnClickListener {
            onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_PHEED
            )
        }

        binding.btnSharePheed1.setOnClickListener {
            onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_PHEED,
                SchemeActivity.PARAM_ID,
                PARAM_ID2,
                ItemData(),
                pheedId = "1"
            )
        }

        binding.btnSharePheed2.setOnClickListener {
            onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_PHEED,
                SchemeActivity.PARAM_ID,
                pheedId = "2"
            )
        }

        binding.btnShareComment1.setOnClickListener {
            onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_COMMENT,
                SchemeActivity.PARAM_ID,
                pheedId = "1000"
            )
        }
        binding.btnShareComment2.setOnClickListener {
            onDynamicLinkClick(
                this,
                SchemeActivity.SCHEME_COMMENT,
                SchemeActivity.PARAM_ID,
                pheedId = "1001"
            )
        }
    }
}