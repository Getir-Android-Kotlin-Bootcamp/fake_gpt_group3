package com.week2.getir.fake_gpt

import com.google.ai.client.generativeai.type.BlockThreshold
import com.google.ai.client.generativeai.type.HarmCategory
import com.google.ai.client.generativeai.type.SafetySetting

// Safety setting list for gemini.
val safetySettingList = listOf(
    SafetySetting(HarmCategory.DANGEROUS_CONTENT, BlockThreshold.NONE),
    SafetySetting(HarmCategory.HARASSMENT, BlockThreshold.NONE),
    SafetySetting(HarmCategory.HATE_SPEECH, BlockThreshold.NONE),
    SafetySetting(HarmCategory.SEXUALLY_EXPLICIT, BlockThreshold.NONE)
)
