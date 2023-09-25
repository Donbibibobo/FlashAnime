package com.example.flashanime.all.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.flashanime.R
import com.example.flashanime.databinding.DialogCategoryBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.material.chip.Chip

class CategoryDialog: AppCompatDialogFragment() {

    private val viewModel by viewModels<CategoryViewModel> { getVmFactory() }
    private lateinit var binding: DialogCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogCategoryBinding.inflate(inflater, container, false)
        binding.layoutCategory.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_up))

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.leave.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    dismiss()
                    viewModel.onLeaveCompleted()
                }
            }
        )

        var selectedChipsText: List<String> = listOf("全部")

        binding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
            selectedChipsText = checkedIds.map { chipId ->
                val chip: Chip = group.findViewById(chipId)
                chip.text.toString()
            }
            Log.i("Selected chips", "Selected chips: $selectedChipsText")
        }


        // default
        binding.chipGroup.children.filter { it is Chip && it.id != binding.chipAll.id }.forEach { chipView ->
            (chipView as Chip).setOnClickListener {
                // default [all]
                if (binding.chipGroup.checkedChipIds.isEmpty()) {
                    binding.chipAll.isChecked = true
                }
                // default [all], select others will cancel [all]
                if (chipView.isChecked && binding.chipAll.isChecked) {
                    binding.chipAll.isChecked = false
                }
            }
        }

        // click [all] cancel other
        binding.chipAll.setOnClickListener {
            if (binding.chipAll.isChecked) {
                binding.chipGroup.children.filter { it is Chip && it.id != binding.chipAll.id }
                    .forEach { (it as Chip).isChecked = false }
            }
        }



        binding.buttonSend.setOnClickListener {

            listener?.onCategoriesSelected(selectedChipsText)

            Log.i("Selected chips", "Selected chips: $selectedChipsText")
            dismiss()
            viewModel.onLeaveCompleted()
        }







        return binding.root
    }

    interface CategoryDialogListener {
        fun onCategoriesSelected(categories: List<String>)
    }

    // let any intense can set the listener itself through setCategoryDialogListener()
    private var listener: CategoryDialogListener? = null
    fun setCategoryDialogListener(listener: CategoryDialogListener) {
        this.listener = listener
    }
}