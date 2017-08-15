package com.codeprinciples.advancedadapterexample.adapters;

import android.databinding.BindingAdapter;
import android.databinding.ObservableList;
import android.support.v7.widget.RecyclerView;

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

public class AttributeBindingsAddapter {
    @BindingAdapter({"bind:list","bind:layoutManager","bind:itemAnimator"})
    public static void setList(RecyclerView rv, ObservableList dataItems, RecyclerView.LayoutManager layoutManager, RecyclerView.ItemAnimator itemAnimator){
        if(rv.getLayoutManager()==null)
            rv.setLayoutManager(layoutManager);
        if(rv.getAdapter() ==null)
            rv.setAdapter(new RecyclerViewBindingAdapter(dataItems));
        if(rv.getItemAnimator() == null)
            rv.setItemAnimator(itemAnimator);
    }
}
