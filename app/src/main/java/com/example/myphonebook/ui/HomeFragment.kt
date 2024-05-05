package com.example.myphonebook.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.myphonebook.MainViewModel
import com.example.myphonebook.R
import com.example.myphonebook.databinding.FragmentHomeBinding
import com.example.myphonebook.util.ContactAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        //damit LiveData autom observed wird vom layout
        binding.lifecycleOwner = this.viewLifecycleOwner

        //inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //ContactListe beobahten
        val contactAdapter = ContactAdapter()
        binding.homeContactsRv.adapter = contactAdapter

        viewModel.contactList.observe(viewLifecycleOwner) { //genial: contactList ist vom viewModel die contactList
            contactAdapter.submitList(it)
        }

        //mit add button zum Detailfragment
        binding.homeAddContactFab.setOnClickListener {
            findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment())
        }
    }

}