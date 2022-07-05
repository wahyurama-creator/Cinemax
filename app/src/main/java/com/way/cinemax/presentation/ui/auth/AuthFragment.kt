package com.way.cinemax.presentation.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.way.cinemax.R
import com.way.cinemax.databinding.FragmentAuthBinding

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSignUp.setOnClickListener {
            activity?.findNavController(R.id.fragmentContainerAuth)
                ?.navigate(R.id.action_authFragment_to_signUpFragment)
        }
        binding.tvLogin.setOnClickListener {
            activity?.findNavController(R.id.fragmentContainerAuth)
                ?.navigate(R.id.action_authFragment_to_loginFragment)
        }
    }
}