package com.example.spacex.screens.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.spacex.R
import com.example.spacex.databinding.FragmentDetailBinding
import com.example.spacex.models.ResponseModelItem
import com.example.spacex.utils.CrewAdapter
import com.example.spacex.viewmodels.DetailViewModel

class DetailFragment : Fragment() {

    lateinit var binding: FragmentDetailBinding
    private lateinit var currentLaunch: ResponseModelItem
    lateinit var recyclerView: RecyclerView
    val adapter by lazy { CrewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        currentLaunch = arguments?.getSerializable("launch") as ResponseModelItem
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        viewModel.getCrew()

        recyclerView = binding.crewRv
        recyclerView.adapter = adapter

        binding.imageView.load(currentLaunch.links.patch.large) {
            placeholder(R.drawable.ic_launcher_foreground)
            transformations(CircleCropTransformation())
        }
        binding.title.text = currentLaunch.name
        binding.quantity.text = currentLaunch.cores.map { it.flight }.toString()
        if (currentLaunch.success) {
            binding.status.text = "Успех"
        } else {
            binding.status.text = "Неудача"
        }
        binding.date.text = currentLaunch.date_local
        binding.details.text = currentLaunch.details

        viewModel.crew.observe(viewLifecycleOwner, {
            adapter.setList(it.body()!!)
        })
    }

}