package com.example.sectionadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView, menuRv;
    private ItemAdapter itemAdapter;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private MenuAdapter menuAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        menuRv = findViewById(R.id.rv_image_view);

        setUpAdapter();
        setUpSection();
    }

    private void setUpAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }

    private void setUpSection() {
        List<Item> items = new ArrayList<>();

        List<SectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();
        List<String> menuList = new ArrayList<>();
        int section =0;
        for (int i = 0; i < 10; i++){
            sections.add(new SectionedRecyclerViewAdapter.Section(section,"Header ="+i));
            menuList.add("Header ="+i);
            for (int j =0; j< 5; j++){
                items.add(new Item("Hello greeting "+j));
                section++;
            }
        }

        itemAdapter = new ItemAdapter(items);
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter(this, R.layout.section,
                R.id.section_text, recyclerView, itemAdapter);
        recyclerView.setAdapter(sectionedRecyclerViewAdapter);
       setSections(sections);
        setUpMenu(menuList);

    }

    private void setSections(List<SectionedRecyclerViewAdapter.Section> sections) {
        SectionedRecyclerViewAdapter.Section[] dummy = new SectionedRecyclerViewAdapter.Section[sections.size()];
        sectionedRecyclerViewAdapter.setSections(sections.toArray(dummy));

    }

    private void setUpMenu(List<String> list){
        MenuAdapter menuAdapter = new MenuAdapter(list);
        menuRv.setAdapter(menuAdapter);
    }
}
