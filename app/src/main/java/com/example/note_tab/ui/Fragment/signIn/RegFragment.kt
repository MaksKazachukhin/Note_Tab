package com.example.noteapp.ui.fragments.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.note_tab.R
import com.example.note_tab.databinding.FragmentRegBinding
import com.example.note_tab.ui.utils.PreferenceHelper
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentSignInBinding
import com.example.noteapp.utils.PreferenceHelper
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentRegBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    private val signInLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    firebaseAuthWithGoogle(account?.idToken)
                } catch (e: ApiException) {
                    updateUi(null)
                }
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
                FragmentRegBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        PreferenceHelper.init(requireContext())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkSP()
        setupListeners()
    }

    private fun checkSP() {
        if (PreferenceHelper.isSignIn) {
            findNavController().navigate(R.id.action_onBoardFragment_to_noteFragment)
        }
    }

    private fun setupListeners() {
        binding.btnReg.setOnClickListener {
            signInLauncher.launch(googleSignInClient.signInIntent)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String?) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    PreferenceHelper.isSignIn = true
                    updateUi(user)
                } else {
                    updateUi(null)
                }
            }
    }

    private fun updateUi(user: FirebaseUser?) {
        if (user != null) {
            findNavController().navigate(R.id.action_signInFragment_to_noteFragment)
        } else {
            Toast.makeText(requireContext(), "Аутентификация не удалась", Toast.LENGTH_SHORT).show()
        }
    }
}
