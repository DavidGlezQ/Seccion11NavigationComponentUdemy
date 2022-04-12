package com.example.barberodormilon.seccion11_navigation_component_udemy.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.barberodormilon.seccion11_navigation_component_udemy.Interfaces.OnClickListener;
import com.example.barberodormilon.seccion11_navigation_component_udemy.Adapters.ProductAdapter;
import com.example.barberodormilon.seccion11_navigation_component_udemy.Pojos.ProductPOJO;
import com.example.barberodormilon.seccion11_navigation_component_udemy.R;

import java.util.ArrayList;
import java.util.List;


public class ProductsFragment extends Fragment implements OnClickListener {

    private final List<ProductPOJO> selectedProduct = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ProductAdapter adapter = new ProductAdapter(getProducts(), this);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.btn_add_car).setOnClickListener(view1 ->{
            //NavHostFragment.findNavController(this).navigate(R.id.action_productsFragment_to_cardFragment));
            ProductsFragmentDirections.ActionProductsFragmentToCardFragment action = ProductsFragmentDirections.actionProductsFragmentToCardFragment();
            action.setProducts(getProductsStr());

            NavHostFragment.findNavController(this).navigate(action);
        });
    }

    private List<ProductPOJO> getProducts() {
        List<ProductPOJO> products = new ArrayList<>();
        Log.d("addProduct", "addProduct");
        products.add(new ProductPOJO("Classic", "https://upload.wikimedia.org/wikipedia/commons/4/48/Dona_sencilla_mexicana.jpg"));
        products.add(new ProductPOJO("Glazed", "https://p0.pikist.com/photos/921/203/donut-mini-donut-small-round-cake-torus-" + "glaze-fat-sugar-mixture-schokoplaettchen.jpg"));
        products.add(new ProductPOJO("Chocolate", "https://cdn.pixabay.com/photo/2017/04/13/23/35/dona-2228986__340.jpg"));
        products.add(new ProductPOJO("Blue Berry", "https://cdn.pixabay.com/photo/2017/11/22/00/18/donuts-2969490_1280.jpg"));
        products.add(new ProductPOJO("Dark Chocolate", "https://cdn.pixabay.com/photo/2017/04/12/21/18/dona-2225812_1280.jpg"));
        products.add(new ProductPOJO("Strawberry", "https://live.staticflickr.com/1338/1036945312_8e12c26d99_b.jpg"));
        products.add(new ProductPOJO("Caramel", "https://p0.pxfuel.com/preview/187/392/25/cute-sweet-tasty-delicious.jpg"));
        products.add(new ProductPOJO("Wine", "https://cdn.pixabay.com/photo/2017/08/11/21/46/donuts-2632878__340.jpg"));
        products.add(new ProductPOJO("Penaut", "https://cdn.pixabay.com/photo/2014/02/17/17/26/donuts-268388__340.jpg"));
        products.add(new ProductPOJO("Apple", "https://cdn.pixabay.com/photo/2020/10/12/15/58/donuts-5649218__340.jpg"));
        products.add(new ProductPOJO("Special", "https://live.staticflickr.com/3356/3282189390_4ba32754b6_b.jpg"));
        return products;
    }

    private String[] getProductsStr(){
        String[] productsStr = new String[selectedProduct.size()];
        int index = 0;
        for (ProductPOJO productPOJO: selectedProduct){
            productsStr[index] = productPOJO.getName();
            index ++;
        }
        return productsStr;
    }

    @Override
    public void onClick(ProductPOJO productPOJO) {
        if (productPOJO.isSelected()){
            selectedProduct.add(productPOJO);
        } else {
            selectedProduct.remove(productPOJO);
        }
    }
}