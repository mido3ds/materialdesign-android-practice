package com.practice.mahmoudadas.materialdesign

import android.content.res.Resources
import android.util.DisplayMetrics


fun Resources.dpToPx(dp: Int): Int = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))