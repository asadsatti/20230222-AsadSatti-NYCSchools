package com.example.nyc_school.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nyc_school.R
import com.example.nyc_school.databinding.FragmentSchoolSatBinding
import com.example.nyc_school.presentation.vm.SchoolSatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SchoolSatFragment : Fragment() {

    private lateinit var binding: FragmentSchoolSatBinding
    private val postViewModel: SchoolSatViewModel by viewModels()

    // https://developer.android.com/guide/navigation/navigation-pass-data#kts
    private val args: SchoolSatFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getSatScore()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSchoolSatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeSatScore()
    }

    private fun getSatScore() {
        val schoolDbn = args.schoolDbn
        schoolDbn?.let {
            postViewModel.getSatScore(it)
        }
    }

    private fun observeSatScore() {
        postViewModel.satScore.observe(viewLifecycleOwner) {
            if (it == null) {
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.not_found_title)
                    .setMessage(getString(R.string.not_found_school_sat_score_message))
                    .setNegativeButton(R.string.button_label_cancel) { _,_ ->
                        findNavController().popBackStack()
                    }
                    .create().show()
            } else {
                binding.run {
                    tvSchoolName.text = it.schoolName
                    divider.visibility = View.VISIBLE
                    tvNumOfSatTestTakersLabel.visibility = View.VISIBLE
                    tvNumOfSatTestTakers.text = it.numOfSatTestTakers
                    satCriticalReadingAvgScoreLabel.visibility = View.VISIBLE
                    satCriticalReadingAvgScore.text = it.satCriticalReadingAvgScore
                    satMathAvgScoreLabel.visibility = View.VISIBLE
                    satMathAvgScore.text = it.satMathAvgScore
                    satWritingAvgScoreLabel.visibility = View.VISIBLE
                    satWritingAvgScore.text = it.satWritingAvgScore
                }
            }
        }
    }
}