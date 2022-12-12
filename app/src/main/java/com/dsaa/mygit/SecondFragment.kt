package com.dsaa.mygit

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentContainer
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dsaa.mygit.api.ApiService
import com.dsaa.mygit.databinding.FragmentSecondBinding
import com.dsaa.mygit.viewmodel.FirstViewModel
import com.dsaa.mygit.viewmodel.SecondViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    val args:SecondFragmentArgs by navArgs()
    val viewModel = viewModels<SecondViewModel>()
    @Inject
    lateinit var service: ApiService

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = args.loginName
        Log.d("Second", "onViewCreated: $name")
        viewModel.value.userData.observe(viewLifecycleOwner) {
            Log.d("TAG", "onViewCreated: "+it.toString())
            binding.tvName2.text = it?.name
            binding.tvBlog2.text = it?.blog
            binding.tvTwitter2.text = it?.twitterUsername as CharSequence?
            Glide
                .with(binding.ivHeader)
                .load(it?.avatarUrl)
                .centerCrop()
                .into(binding.ivHeader)
        }
        if (name != null) {
            viewModel.value.getUserData(name)
        }


//        binding.buttonSecond.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}