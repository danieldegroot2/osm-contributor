/**
 * Copyright (C) 2016 eBusiness Information
 * <p>
 * This file is part of OSM Contributor.
 * <p>
 * OSM Contributor is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * OSM Contributor is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with OSM Contributor.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.jawg.osmcontributor.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.jawg.osmcontributor.R;
import io.jawg.osmcontributor.model.entities.relation_display.RelationDisplay;
import io.jawg.osmcontributor.ui.adapters.parser.BusLineRelationDisplayParser;

public class BusLineSuggestionAdapter extends ArrayAdapter<RelationDisplay> {

    private static final int LAYOUT = R.layout.simple_dropdown_item;

    private LayoutInflater layoutInflater;
    private List<RelationDisplay> suggestionsList;
    private BusLineRelationDisplayParser busLineRelationDisplayParser;

    public BusLineSuggestionAdapter(Context context, BusLineRelationDisplayParser busLineRelationDisplayParser) {
        super(context, LAYOUT);
        layoutInflater = LayoutInflater.from(context);
        this.busLineRelationDisplayParser = busLineRelationDisplayParser;
        suggestionsList = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return suggestionsList.size();
    }

    @Override
    public RelationDisplay getItem(int position) {
        return suggestionsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = layoutInflater.inflate(LAYOUT, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bind(busLineRelationDisplayParser.getBusLine(suggestionsList.get(position)));

        return view;
    }

    private static class ViewHolder {
        private final TextView textView;

        public ViewHolder(View root) {
            textView = root.findViewById(android.R.id.text1);
        }

        public void bind(String item) {
            textView.setText(item);
        }
    }

    public void setBusLinesSuggestion(List<RelationDisplay> suggestionsList) {
        this.suggestionsList = suggestionsList;
    }
}
