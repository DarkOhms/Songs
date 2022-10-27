package com.example.songs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.songs.databinding.FragmentRatingBinding
import com.example.songs.databinding.FragmentSongBinding
import com.example.songs.model.SongViewModel
import com.example.songs.model.SongViewModelFactory
import com.example.songs.model.SongWithRatings

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RatingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RatingFragment : DialogFragment(R.layout.fragment_rating) {
    private lateinit var param1song: SongWithRatings
    lateinit var binding: FragmentRatingBinding

    //shared view model for use in the fragment
    private val songViewModel: SongViewModel by activityViewModels { SongViewModelFactory((requireActivity().application as SongApplication).repository) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            //param1 = it.getString(ARG_PARAM1)

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRatingBinding.bind(view)
        binding.songTitle.text = param1song.song.songTitle
        //make history button to open history fragment
        val history: Button = binding.history
        history.setOnClickListener{
            launchRatingHistory(param1song)
            this.dismiss()
        }

    }

    private fun launchRatingHistory(song: SongWithRatings) {
        // Create an instance of the ratingHistory fragment and show it
       /*
       9/22/22
       Really annoyed with this.  I'm sure there's a better way but apparently the DialogFragment
       isn't considered the current destination fragment so I'll use an if statement and generate extra actions.

       Currently only have mainFragment as starting destination for navigation
        */

        //determine currentDestination fragment for action then navigate
        if(findNavController().currentDestination?.id==R.id.mainFragment) {
            val action =
                MainFragmentDirections.actionMainFragmentToRatingHistoryFragment(song.song.songTitle)

            findNavController().navigate(action)
        }




    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RatingFragment.
         */
        // TODO: Rename and change types and number of parameters

        @JvmStatic
        fun newInstance(param1: SongWithRatings) =
            RatingFragment().apply {
                arguments = Bundle().apply {
                    param1song = param1
                }
            }
    }
}