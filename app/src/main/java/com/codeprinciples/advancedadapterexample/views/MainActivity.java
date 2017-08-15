package com.codeprinciples.advancedadapterexample.views;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.codeprinciples.advancedadapterexample.BR;
import com.codeprinciples.advancedadapterexample.R;
import com.codeprinciples.advancedadapterexample.Utils;
import com.codeprinciples.advancedadapterexample.adapters.RecyclerViewBindingAdapter;
import com.codeprinciples.advancedadapterexample.databinding.ActivityMainBinding;
import com.codeprinciples.advancedadapterexample.models.HeadingModel;
import com.codeprinciples.advancedadapterexample.models.ItemModel;
import com.codeprinciples.advancedadapterexample.models.SubItemModel;
import com.codeprinciples.advancedadapterexample.presenters.ListItemsPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * MIT License
 * <p>
 * Copyright (c) 2017 Oleksiy
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class MainActivity extends AppCompatActivity implements ListItemsPresenter{
    private static String TAG = "MainActivity";
    private ActivityMainBinding mBinding ;
    private ObservableList<RecyclerViewBindingAdapter.AdapterDataItem> listItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setListLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mBinding.setModelList(initList());
        mBinding.setItemAnimator(new DefaultItemAnimator());
        loadDataWithDelay(1500);
    }

    private void loadDataWithDelay(int delayMilli) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listItems.addAll(listItems.size()-1,getItems());//insert before loading cell
                listItems.remove(listItems.size()-1);//remove loading cell
                listItems.add(new RecyclerViewBindingAdapter.AdapterDataItem(R.layout.layout_load_more, new Pair<Integer, Object>(BR.presenter,MainActivity.this)));
            }
        },delayMilli);
    }

    private ObservableList initList() {
        listItems = new ObservableArrayList<>();
        listItems.add(new RecyclerViewBindingAdapter.AdapterDataItem(R.layout.layout_heading, new Pair<Integer, Object>(BR.headingModel, new HeadingModel("Content Heading"))));
        listItems.add(new RecyclerViewBindingAdapter.AdapterDataItem(R.layout.layout_loading));
        return listItems;
    }

    private List<RecyclerViewBindingAdapter.AdapterDataItem> getItems() {
        List list = new ArrayList();
        list.add(Utils.convert(new ItemModel("Item Content Text"),this));
        list.add(Utils.convert(new ItemModel("Item Content Text"),this));
        list.add(Utils.convert(new ItemModel("Item Content Text"),this));
        list.add(Utils.convert(new ItemModel("Item Content Text"),this));
        list.add(Utils.convert(new ItemModel("Item Content Text"),this));
        return list;
    }

    private List<RecyclerViewBindingAdapter.AdapterDataItem> getSubItems(ItemModel itemModel) {
        List list = new ArrayList();
        for(SubItemModel subItemModel : itemModel.subItemModels){
            list.add(Utils.convert(subItemModel,this));
        }
        return list;
    }

    @Override
    public void onClick(ItemModel itemModel) {
        Toast.makeText(this, "itemModel clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(SubItemModel subItemModel) {
        Toast.makeText(this, "subItemModel clicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(ItemModel itemModel) {
        if(itemModel.expanded)
            onExpandClick(itemModel);
        listItems.remove(Utils.convert(itemModel, this));
    }

    @Override
    public void onExpandClick( ItemModel itemModel) {
        itemModel.expanded = !itemModel.expanded;
        RecyclerViewBindingAdapter.AdapterDataItem item = Utils.convert(itemModel, this);
        if(itemModel.expanded) {
            listItems.addAll(listItems.indexOf(item)+1, getSubItems(itemModel));
        }else {
            int index = listItems.indexOf(item)+1;
            listItems.subList(index, index + itemModel.subItemModels.size()).clear();
        }
    }

    @Override
    public void onLoadMoreClick() {
        Log.i(TAG,"load more clicked");
        listItems.remove(listItems.size()-1);//remove load more cell
        listItems.add(new RecyclerViewBindingAdapter.AdapterDataItem(R.layout.layout_loading)); //add loading cell
        loadDataWithDelay(1500);
    }
}
