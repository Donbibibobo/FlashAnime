package com.example.flashanime.util

import com.example.flashanime.R
import com.example.flashanime.util.Util.getString


enum class CurrentFragmentType(val value: String) {
    HOME(getString(R.string.first_home)),
    ALL(getString(R.string.first_all)),
    COLLECTED(getString(R.string.first_collected)),
    VOCABULARY(getString(R.string.first_vocabulary)),
    PROFILE(getString(R.string.first_profile)),
    DETAIL(""),

    VOCABULARY_DETAIL(getString(R.string.vocabulary_detail)),
    WORD_TEST(getString(R.string.word_test)),

    PROFILE_COLLECTED(getString(R.string.first_collected)),
    WATCH_HISTORY(getString(R.string.watch_history)),
}