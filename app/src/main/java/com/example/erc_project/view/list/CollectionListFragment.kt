package com.example.erc_project.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.erc_project.R
import com.example.erc_project.mdoel.data.CollectionItem
import com.example.erc_project.view.BaseFragment
import androidx.fragment.app.activityViewModels
import com.example.erc_project.view.main.CollectionViewModel


class CollectionListFragment : BaseFragment() {

    private val viewModel by activityViewModels<CollectionViewModel>()
    private val collectionAdapter = CollectionAdapter(::turnToDetailFragment)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_collection_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBaseViewModel(viewModel)

        view.findViewById<RecyclerView>(R.id.rv_fragment_collection_list).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = collectionAdapter
        }


        viewModel.getCollectionList().observe(viewLifecycleOwner, Observer {
            collectionAdapter.submitList(it)
        })
    }


    private fun turnToDetailFragment(item: CollectionItem) {
        viewModel.selectCollectionItem(item)

        //記得解註冊，不然到下一頁會出現 loading 卡住的情況
        unregisterBaseViewModel(viewModel)

        findNavController().navigate(R.id.action_fragment_list_to_fragment_detail)
    }
}