package com.example.spacex.screens.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.spacex.R
import com.example.spacex.databinding.FragmentMainBinding
import com.example.spacex.models.ResponseModelItem
import com.example.spacex.utils.MAIN
import com.example.spacex.utils.MainAdapter
import com.example.spacex.viewmodels.MainViewModel

class MainFragment : Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var recyclerView: RecyclerView
    val adapter by lazy { MainAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLaunches()
        recyclerView = binding.mainRv
        recyclerView.adapter = adapter
        viewModel.launches.observe(viewLifecycleOwner, {
            adapter.setList(it.body()!!)
        })

    }

    companion object {
        fun clickLaunch(model: ResponseModelItem) {
            val bundle = Bundle()
            bundle.putSerializable("launch", model)
            Navigation.findNavController(MAIN, R.id.mainFragment).navigate(R.id.action_mainFragment_to_detailFragment, bundle)
        }
    }

}