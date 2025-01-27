package com.codetech.fullscreennative

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.codetech.fullscreennative.databinding.ActivityNativeShow2Binding
import com.codetech.fullscreennative.databinding.ActivityNativeShowBinding
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MediaAspectRatio
import com.google.android.gms.ads.VideoController.VideoLifecycleCallbacks
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

class NativeShowActivity2 : AppCompatActivity() {
    private val binding by lazy { ActivityNativeShow2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        loadNativeAd()
    }

    private fun loadNativeAd(){
        binding.shimmerViewContainer.startShimmer()
        val adOptions = NativeAdOptions.Builder()
            .setMediaAspectRatio(MediaAspectRatio.SQUARE)
            .build()

        val adLoader = AdLoader.Builder(this, getString(R.string.admob_native_full_screen_ad_id))
            .forNativeAd { nativeAd ->
                val nativeAdView = binding.nativeAdView
                binding.adCallToAction.visibility = View.VISIBLE
                binding.adAppIcon.setBackgroundColor(Color.TRANSPARENT)
                binding.adHeadline.setBackgroundColor(Color.TRANSPARENT)
                binding.adBody.setBackgroundColor(Color.TRANSPARENT)
                populateNativeAdView(nativeAd, nativeAdView)
            }
            .withAdListener(object : AdListener() {
                override fun onAdLoaded() {
                    super.onAdLoaded()
                    binding.shimmerViewContainer.stopShimmer()
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    binding.shimmerViewContainer.stopShimmer()
                }
            })
            .withNativeAdOptions(adOptions)
            .build()

        adLoader.loadAd(AdRequest.Builder().build())
    }

    private fun populateNativeAdView(nativeAd: NativeAd, adView: NativeAdView) {
        adView.mediaView = binding.adMedia
        adView.headlineView = binding.adHeadline
        adView.bodyView = binding.adBody
        adView.callToActionView = binding.adCallToAction
        val imageView: ImageView = binding.adAppIcon
        imageView.clipToOutline = true
        adView.iconView = imageView

        binding.adHeadline.text = nativeAd.headline
        binding.adMedia.mediaContent = nativeAd.mediaContent

        if (nativeAd.body == null) {
            binding.adBody.visibility = View.INVISIBLE
            binding.adBody.text = ""
        } else {
            binding.adBody.visibility = View.VISIBLE
            binding.adBody.text = nativeAd.body
        }

        if (nativeAd.callToAction == null) {
            binding.adCallToAction.visibility = View.INVISIBLE
            binding.adBody.text = ""
        } else {
            binding.adCallToAction.visibility = View.VISIBLE
            binding.adCallToAction.text = nativeAd.callToAction
        }

        if (nativeAd.icon == null) {
            binding.adAppIcon.visibility = View.GONE
        } else {
            binding.adAppIcon.visibility = View.VISIBLE
            binding.adAppIcon.setImageDrawable(nativeAd.icon!!.drawable)
        }

        adView.setNativeAd(nativeAd)

        val videoController = nativeAd.mediaContent!!.videoController

        if (videoController.hasVideoContent()) {
            videoController.videoLifecycleCallbacks = object : VideoLifecycleCallbacks() {
                override fun onVideoEnd() {
                    super.onVideoEnd()
                }
            }
        }
    }
}