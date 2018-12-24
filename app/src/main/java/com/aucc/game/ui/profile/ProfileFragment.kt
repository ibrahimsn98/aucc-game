package com.aucc.game.ui.profile

import android.os.Bundle
import android.view.View
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

        binding.progressText.text = String.format("%1d /%2d", prefUtils.getCompletedLevelCount(), prefUtils.getLevelCount())
        binding.progress.max = prefUtils.getLevelCount()
        binding.progress.progress = prefUtils.getCompletedLevelCount()
    }
}