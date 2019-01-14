package com.aucc.game.ui.profile

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.aucc.game.R
import com.aucc.game.base.BaseFragment
import com.aucc.game.databinding.FragmentProfileBinding
import com.aucc.game.ui.main.MainActivity
import com.aucc.game.util.PrefUtils
import javax.inject.Inject

class ProfileFragment : BaseFragment<MainActivity, FragmentProfileBinding>() {

    @Inject lateinit var prefUtils: PrefUtils

    override fun layoutRes(): Int {
        return R.layout.fragment_profile
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.prefNotificationsSw.isChecked = prefUtils.isSystemNotificationsEnabled()

        binding.prefNotificationsSw.setOnCheckedChangeListener { _, isChecked ->
            prefUtils.setSystemNotificationsEnabled(isChecked)
        }

        binding.prefNotifications.setOnClickListener {
            binding.prefNotificationsSw.isChecked = !binding.prefNotificationsSw.isChecked
        }

        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.progressText.text = String.format("%1d /%2d", prefUtils.getCompletedLevelCount(), prefUtils.getLevelCount())
        binding.progress.max = 100

        val progress = (prefUtils.getCompletedLevelCount().toFloat() / prefUtils.getLevelCount().toFloat() * 100).toInt()
        val progressAnimator = ValueAnimator.ofInt(0, progress)
        val scoreAnimator = ValueAnimator.ofInt(0, prefUtils.getCompletedLevelCount() * 50)

        progressAnimator.addUpdateListener { animation -> binding.progress.progress = animation.animatedValue as Int }
        scoreAnimator.addUpdateListener { animation -> binding.score.text = resources.getString(R.string.profile_score, (animation.animatedValue as Int)) }

        progressAnimator.start()
        scoreAnimator.start()
    }
}