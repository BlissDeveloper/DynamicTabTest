package com.abizo.dynamictabtest;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {
    private CollectionReference tabItemsRef;

    private String tabName;
    private View mView;
    private TextView textViewName;
    private RecyclerView recyclerViewTabItem;
    private LinearLayoutManager linearLayoutManager;

    public static TabFragment newInstance(int pos, String name) {
        TabFragment tabFragment = new TabFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        tabFragment.setArguments(args);
        return tabFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_tab, container, false);

        tabItemsRef = FirebaseFirestore.getInstance().collection("Items");
        recyclerViewTabItem = mView.findViewById(R.id.recyclerViewItems);
        linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);

        tabName = getArguments().getString("name");

        //textViewName = mView.findViewById(R.id.textViewFragmentName);
        //textViewName.setText(tabName);

        recyclerViewTabItem.setLayoutManager(linearLayoutManager);

        loadItems();

        return mView;
    }

    public void loadItems() {
        Query query = tabItemsRef.whereEqualTo("name", tabName);

        FirestoreRecyclerOptions<TabItem> options = new FirestoreRecyclerOptions.Builder<TabItem>().setQuery(query, TabItem.class).build();

        final FirestoreRecyclerAdapter<TabItem, TabItemViewHolder> firestoreRecyclerAdapter = new FirestoreRecyclerAdapter<TabItem, TabItemViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull TabItemViewHolder holder, int position, @NonNull TabItem model) {
                holder.setBody(model.getBody());
            }

            @NonNull
            @Override
            public TabItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_item_layout, parent, false);
                return new TabItemViewHolder(view);
            }
        };
        recyclerViewTabItem.setAdapter(firestoreRecyclerAdapter);
        firestoreRecyclerAdapter.startListening();
    }

}
