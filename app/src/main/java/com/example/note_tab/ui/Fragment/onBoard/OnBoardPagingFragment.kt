package com.example.note_tab.ui.Fragment.onBoard

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.note_tab.R
import com.example.note_tab.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardPagingBinding

    companion object {
        const val ARG_ONBOARD_POSITION = "onBoardPostion"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    @SuppressLint("ResourceType")
    private fun initialize() = with(binding) {
        when (requireArguments().getInt(ARG_ONBOARD_POSITION)) {
            0 -> {
                tvHead.text = "Удобство"
                tvBody.text =
                    "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
                lottieAnimation.setAnimation(R.raw.lotti1)
            }

            1 -> {
                tvHead.text = "Организация"
                tvBody.text =
                    "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
                lottieAnimation.setAnimation(R.raw.lotti2)
            }

            2 -> {
                tvHead.text = "Синхронизация"
                tvBody.text =
                    "Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
                lottieAnimation.setAnimation(R.raw.lotti3)
            }
        }
    }
}