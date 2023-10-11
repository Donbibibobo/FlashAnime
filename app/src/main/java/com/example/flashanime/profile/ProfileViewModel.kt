package com.example.flashanime.profile

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flashanime.R
import com.example.flashanime.data.source.FlashAnimeRepository
import com.example.flashanime.databinding.CustomDialogLayoutBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlin.random.Random

class ProfileViewModel(private val flashAnimeRepository: FlashAnimeRepository): ViewModel() {


    private val _leaveDialog = MutableLiveData<Boolean?>()
    val leaveDialog: LiveData<Boolean?>
        get() = _leaveDialog


    fun showAbout(context: Context) {

        val binding = CustomDialogLayoutBinding.inflate(LayoutInflater.from(context))

        val dialog = MaterialAlertDialogBuilder(context)
            .setView(binding.root)
            .create()

        binding.confirmButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.setOnDismissListener {
            _leaveDialog.value = true
        }

        dialog.show()
    }

    fun leaveDialogComplete() {
        _leaveDialog.value = null
    }
}