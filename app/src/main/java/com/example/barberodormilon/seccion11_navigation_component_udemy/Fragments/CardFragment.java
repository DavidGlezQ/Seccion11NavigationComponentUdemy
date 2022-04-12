package com.example.barberodormilon.seccion11_navigation_component_udemy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.barberodormilon.seccion11_navigation_component_udemy.Adapters.CardAdapter;
import com.example.barberodormilon.seccion11_navigation_component_udemy.R;

public class CardFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        CardAdapter cardAdapter = new CardAdapter(CardFragmentArgs.fromBundle(getArguments()).getProducts());
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(cardAdapter);

        TextView tvSum = view.findViewById(R.id.tvSum);
        tvSum.setText(getString(R.string.car_sum, (float)cardAdapter.getItemCount()));

        view.findViewById(R.id.btnBack).setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_cardFragment_to_productsFragment));
        view.findViewById(R.id.btnPay).setOnClickListener(view1 -> NavHostFragment.findNavController(this).navigate(R.id.action_cardFragment_to_conformationFragment));
    }
}