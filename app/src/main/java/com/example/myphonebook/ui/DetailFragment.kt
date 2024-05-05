package com.example.myphonebook.ui

import android.annotation.SuppressLint
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
import com.example.myphonebook.data.datamodel.Contact
import com.example.myphonebook.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_detail, container, false
        )
        //damit LiveData autom observed wird vom layout
        binding.lifecycleOwner = this.viewLifecycleOwner

        //inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailSaveBtn.setOnClickListener {
            val firstName = binding.detailFirstNameTv.text.toString()
            val lastName = binding.detailLastNameTv.text.toString()
            val phoneNumber = binding.detailPhoneNumberTv.text.toString()
            val relationShip = binding.detailRelationshipSp.selectedItem.toString()     //Spinner auslesen

            //neuen Kontakt erstellen
            val newContact = Contact(firstName = firstName, lastName = lastName, phoneNumber = phoneNumber, relationship = relationShip)    //Schreibweise weil keine ID angegebn wird, da diese automatisch erstellt wird
            viewModel.insert(newContact)

            //bei Cancel zurück navigieren zum HomeFragment
            findNavController()
                .navigate(DetailFragmentDirections.actionDetailFragmentToHomeFragment())
        }
    }
}