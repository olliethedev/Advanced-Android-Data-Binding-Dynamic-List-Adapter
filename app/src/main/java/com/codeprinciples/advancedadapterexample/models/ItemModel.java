package com.codeprinciples.advancedadapterexample.models;

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

public class ItemModel {
    public String item;
    public boolean expanded;
    public List<SubItemModel> subItemModels;

    public ItemModel(String item) {
        this.item = item;
        this.subItemModels = generateSubItems();
    }

    private List<SubItemModel> generateSubItems() {
        List<SubItemModel> subItems = new ArrayList<>();
        subItems.add(new SubItemModel("Sub Item Content Text"));
        subItems.add(new SubItemModel("Sub Item Content Text"));
        subItems.add(new SubItemModel("Sub Item Content Text"));
        subItems.add(new SubItemModel("Sub Item Content Text"));
        subItems.add(new SubItemModel("Sub Item Content Text"));
        return subItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemModel itemModel = (ItemModel) o;

        if (expanded != itemModel.expanded) return false;
        if (item != null ? !item.equals(itemModel.item) : itemModel.item != null) return false;
        return subItemModels != null ? subItemModels.equals(itemModel.subItemModels) : itemModel.subItemModels == null;

    }

    @Override
    public int hashCode() {
        int result = item != null ? item.hashCode() : 0;
        result = 31 * result + (expanded ? 1 : 0);
        result = 31 * result + (subItemModels != null ? subItemModels.hashCode() : 0);
        return result;
    }
}
