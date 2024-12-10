package com.example.note_tab.ui.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.note_tab.databinding.FragmentOnBoardPagingBinding

class OnBoardPagingFragment : Fragment() {
    private lateinit var binding: FragmentOnBoardPagingBinding

    companion object{
        const val ARG_ONBOARD_POSITION="onBoardPostion"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOnBoardPagingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() = with(binding) {
        when(requireArguments().getInt(ARG_ONBOARD_POSITION)){
            0 -> {
                tvHead.text = "Удобство"
                tvBody.text = "Создавайте заметки в два клика! Записывайте мысли, идеи и важные задачи мгновенно."
            }
            1 -> {
                tvHead.text = "Организация"
                tvBody.text = "Организуйте заметки по папкам и тегам. Легко находите нужную информацию в любое время."
            }
            2 ->{
                tvHead.text="Синхронизация"
                tvBody.text="Синхронизация на всех устройствах. Доступ к записям в любое время и в любом месте."
            }
        }
    }
}