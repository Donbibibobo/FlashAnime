package com.example.flashanime.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.core.view.children
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.flashanime.MainViewModel
import com.example.flashanime.NavigationDirections
import com.example.flashanime.R
import com.example.flashanime.all.category.CategoryViewModel
import com.example.flashanime.databinding.DialogCategoryBinding
import com.example.flashanime.databinding.DialogLoginBinding
import com.example.flashanime.ext.getVmFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.chip.Chip
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginDialog: AppCompatDialogFragment() {

    private val viewModel by viewModels<LoginViewModel> { getVmFactory() }
    private lateinit var binding: DialogLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val mainActivityViewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.Dialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DialogLoginBinding.inflate(inflater, container, false)
        binding.layoutLogin.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_slide_up))

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.leave.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    dismiss()
                    viewModel.onLeaveCompleted()
                    mainActivityViewModel.backToHome()
                }
            }
        )



        // google singIn
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.googleLogin.setOnClickListener {
            googleSignIn()
        }




        val currentUser = auth.currentUser
        if (currentUser != null) {
            Log.i("googleLogin","1currentUser != null")
            binding.googleLogin.visibility = View.GONE
        } else {
            Log.i("googleLogin","2currentUser == null")
            binding.googleLogout.visibility = View.GONE
        }

        val uid = currentUser?.uid
        Log.i("googleLogin","3uid: $uid")

        binding.googleLogout.setOnClickListener {
            // logout Firebase
            auth.signOut()

            // logout GoogleSignInClient
            googleSignInClient.signOut().addOnCompleteListener {
//                Toast.makeText(requireContext(), "Logged out successfully", Toast.LENGTH_SHORT).show()
            }

            // refresh auth
            mainActivityViewModel.getAuthInstance()

            // nav to home
            mainActivityViewModel.backToHome()


        }






        return binding.root
    }


    private fun googleSignIn() {

        val signInClient = googleSignInClient.signInIntent
        launcher.launch(signInClient)
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->

        if (result.resultCode == Activity.RESULT_OK){
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            manageResults(task)
        }

    }
    private fun dismissDialog(){
        // refresh auth
        mainActivityViewModel.getAuthInstance()

        // nav to home
        mainActivityViewModel.backToHome()

        dismiss()
    }


    private fun manageResults(task: Task<GoogleSignInAccount>) {
        val account: GoogleSignInAccount? = task.result

        if (account != null){
            val credential = GoogleAuthProvider.getCredential(account.idToken, null)
            auth.signInWithCredential(credential).addOnCompleteListener {

                if (task.isSuccessful){

//                    Toast.makeText(requireContext(), "Account created", Toast.LENGTH_SHORT).show()

                    dismissDialog()


                }else{
//                    Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT).show()

                }
            }
        }else {
//            Toast.makeText(requireContext(), task.exception.toString(), Toast.LENGTH_SHORT).show()

        }
    }

}