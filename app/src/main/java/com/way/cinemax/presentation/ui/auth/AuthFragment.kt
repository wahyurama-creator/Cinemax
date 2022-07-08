package com.way.cinemax.presentation.ui.auth

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.way.cinemax.R
import com.way.cinemax.databinding.FragmentAuthBinding
import com.way.cinemax.presentation.util.Track

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(CLIENT_ID)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.btnSignUp.setOnClickListener {
            navTo(R.id.action_authFragment_to_signUpFragment, null)
        }
        binding.tvLogin.setOnClickListener {
            navTo(R.id.action_authFragment_to_loginFragment, null)
        }
        binding.btnSignInGoogle.setOnClickListener {
            signIn()
        }
    }

    private fun navTo(@IdRes destination: Int, bundle: Bundle?) {
        if (bundle == null) {
            activity?.findNavController(R.id.fragmentContainerAuth)
                ?.navigate(destination)
        } else {
            activity?.findNavController(R.id.fragmentContainerAuth)
                ?.navigate(destination, bundle)
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Track.log(Track.FIREBASE_AUTH, "Sign In Success")
                    updateUI(auth.currentUser)
                } else {
                    Track.log(Track.FIREBASE_AUTH, "Sign In Failed because ${it.exception}")
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val bundle = Bundle().apply { putString(EXTRA_NAME, user.displayName) }
            navTo(R.id.action_authFragment_to_loginFragment, bundle)
        }
    }

    private var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    Track.log(Track.FIREBASE_AUTH, Track.FIREBASE_AUTH + account.id)
                    account.idToken?.let { firebaseAuthWithGoogle(it) }
                } catch (e: Exception) {
                    Track.log(Track.FIREBASE_AUTH, "Sign in failed because $e")
                }
            }
        }

    companion object {
        const val CLIENT_ID =
            "182262593642-r0h10oje6uaif66hs395ogqoa96eqbvj.apps.googleusercontent.com"
        const val EXTRA_NAME = "extra_name"
    }
}