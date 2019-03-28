package com.example.sectionadapter;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MenuAdapter.Listener {
    private RecyclerView recyclerView, menuRv;
    private ItemAdapter itemAdapter;
    private SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter;
    private MenuAdapter menuAdapter;
    private LinearLayoutManager mainRecyclerViewManager;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        menuRv = findViewById(R.id.rv_image_view);
        imageView = findViewById(R.id.iv);
        setUpAdapter();
        setUpSection();
    }

    private void setUpAdapter() {
        mainRecyclerViewManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mainRecyclerViewManager);
        menuRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void setUpSection() {
        List<Item> items = new ArrayList<>();

        List<SectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();
        List<String> menuList = new ArrayList<>();
        int section = 0;
        for (int i = 0; i < 10; i++) {
            sections.add(new SectionedRecyclerViewAdapter.Section(section, "Header =" + i));
            menuList.add("Header =" + i);
            for (int j = 0; j < 5; j++) {
                items.add(new Item("Hello greeting " + j));
                section++;
            }
        }

        itemAdapter = new ItemAdapter(items);
        sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter(this, R.layout.section,
                R.id.section_text, recyclerView, itemAdapter);
        recyclerView.setAdapter(sectionedRecyclerViewAdapter);
        setSections(sections);
        setUpMenu(menuList);
        setScrollListener();
    }


    private int visiblePosition;

    private void setScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int sectionPos = sectionedRecyclerViewAdapter.getSectionPosition(visiblePosition);
                    scrollHeaderMenu(sectionPos);
                    //menuRv.scrollToPosition(sectionPos);
                    Log.e("Section_size", "Section position =" + sectionPos);
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                visiblePosition = mainRecyclerViewManager.findFirstCompletelyVisibleItemPosition();
                Log.e("Section_size", "Scroll position =" + visiblePosition);
            }

        });
    }

    private void setSections(List<SectionedRecyclerViewAdapter.Section> sections) {
        SectionedRecyclerViewAdapter.Section[] dummy = new SectionedRecyclerViewAdapter.Section[sections.size()];
        sectionedRecyclerViewAdapter.setSections(sections.toArray(dummy));

    }

    private void setUpMenu(List<String> list) {
        menuAdapter = new MenuAdapter(list, this);
        menuRv.setAdapter(menuAdapter);
    }

    private void scrollHeaderMenu(int position){
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(MainActivity.this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_ANY;
            }
        };

        smoothScroller.setTargetPosition(position);
        LinearLayoutManager manager = (LinearLayoutManager) menuRv.getLayoutManager();
        manager.startSmoothScroll(smoothScroller);
        showImage(position);

    }

    @Override
    public void onMenuClicked(String title) {
        LinearSmoothScroller smoothScroller = new LinearSmoothScroller(MainActivity.this) {
            @Override
            protected int getVerticalSnapPreference() {
                return LinearSmoothScroller.SNAP_TO_START;
            }

        };

        int position = sectionedRecyclerViewAdapter.getSectionPosition(title);
        smoothScroller.setTargetPosition(position);
        LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
        manager.startSmoothScroll(smoothScroller);
    }

    private void showImage(int position){
        if(position == 0){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_gmail_48dp));
        }else if(position == 1){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_google_48dp));
        }else if(position == 2){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_hangouts_48dp));
        }else if(position == 3){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_inbox_48dp));
        }else if(position == 4){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_keep_48dp));
        }else if(position == 5){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_messenger_48dp));
        }else if(position == 6){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_photos_48dp));
        }else if(position == 7){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_sheets_48dp));
        }else if(position == 8){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_slides_48dp));
        }else if(position == 9){
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_drive_48dp));
        }
        menuAdapter.setSelectedPosition(position);

    }
}
