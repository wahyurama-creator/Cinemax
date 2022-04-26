package com.way.cinemax.presentation.ui.onboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.*
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.way.cinemax.R
import com.way.cinemax.databinding.ActivityOnboardBinding
import com.way.cinemax.presentation.ui.auth.AuthActivity

class OnboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardBinding
    private lateinit var onboardAdapter: OnboardAdapter
    private lateinit var indicatorLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnboardItems()
        setIndicator()
        setCurrentIndicatorPosition(0)
    }

    private fun setOnboardItems() {
        onboardAdapter = OnboardAdapter()
        onboardAdapter.diffList.submitList(
            listOf(
                OnboardItem(
                    R.drawable.onboard_first,
                    R.string.onboard_title_first,
                    R.string.onboard_desc_first
                ),
                OnboardItem(
                    R.drawable.onboard_second,
                    R.string.onboard_title_second,
                    R.string.onboard_desc_second
                ),
                OnboardItem(
                    R.drawable.onboard_third,
                    R.string.onboard_title_third,
                    R.string.onboard_desc_third
                ),
            )
        )
        binding.sliderOnboard.apply {
            adapter = onboardAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    setCurrentIndicatorPosition(position)
                }
            })
        }
    }

    private fun setIndicator() {
        indicatorLayout = binding.layoutIndicator
        val indicators = arrayOfNulls<ImageView>(onboardAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.indicator_bg_disabled)
                )
                it.layoutParams = layoutParams
                indicatorLayout.addView(it)
            }
        }
        (binding.sliderOnboard.getChildAt(0) as RecyclerView).overScrollMode = OVER_SCROLL_NEVER

        binding.tvNext.setOnClickListener {
            if (binding.sliderOnboard.currentItem + 1 < onboardAdapter.diffList.currentList.size) {
                binding.sliderOnboard.currentItem += 1
            } else {
                navigateToSignIn()
            }
        }

        binding.btnSkip.setOnClickListener {
            navigateToSignIn()
        }
    }

    private fun setCurrentIndicatorPosition(position: Int) {
        val childCount = indicatorLayout.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorLayout.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_bg_active
                    )
                )

            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_bg_disabled
                    )
                )
            }
        }
    }

    private fun navigateToSignIn() {
        startActivity(Intent(this, AuthActivity::class.java))
        finish()
    }
}