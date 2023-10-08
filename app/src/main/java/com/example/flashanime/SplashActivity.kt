package com.example.flashanime

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateInterpolator
import android.widget.ImageView
import com.airbnb.lottie.LottieAnimationView
import kotlin.math.log

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        fun navigateToMainActivity(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }

        val lottieView = findViewById<LottieAnimationView>(R.id.lottie_splash)
        lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {}
            override fun onAnimationEnd(p0: Animator) {

                Handler().postDelayed({
                    lottieView.animate().scaleX(10f).scaleY(10f)
                        .alpha(0f).setInterpolator(AccelerateInterpolator())
                        .setDuration(1000).start()

                    navigateToMainActivity()
                }, 500)
            }
            override fun onAnimationCancel(p0: Animator) {}
            override fun onAnimationRepeat(p0: Animator) {}
        })

    }
}