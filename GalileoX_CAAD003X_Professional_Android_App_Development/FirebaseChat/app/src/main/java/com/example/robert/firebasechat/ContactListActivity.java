package com.example.robert.firebasechat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.robert.firebasechat.lib.ImageLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactListActivity extends AppCompatActivity
        implements ContactListView, OnItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewContacts)
    RecyclerView recyclerViewContacts;

    private ContactListAdapter adapter;
    private ContactListPresenter contactListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        ButterKnife.bind(this);

        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        setupAdapter();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        contactListPresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        contactListPresenter.onPause();
    }

    @Override
    protected void onDestroy() {
        contactListPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void setupAdapter() {
        AndroidChatApplication app = (AndroidChatApplication) getApplication();
        ImageLoader imageLoader = app.getImageLoader();
        adapter = new ContactListAdapter(new ArrayList<User>(), imageLoader, this);
    }

    private void setupRecyclerView() {
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewContacts.setAdapter(adapter);
    }

    @OnClick(R.id.fab)
    public void addContact() {
        // TODO
    }

    @Override
    public void onContactAdded(User user) {
        adapter.add(user);
    }

    @Override
    public void onContactChanged(User user) {
        adapter.update(user);
    }

    @Override
    public void onContactRemoved(User user) {
        adapter.remove(user);
    }

    @Override
    public void onItemClick(User user) {
        // Todo start ChatActivity
    }

    @Override
    public void onItemLongClick(User user) {
        contactListPresenter.removeContact(user.getEmail());
    }
}
