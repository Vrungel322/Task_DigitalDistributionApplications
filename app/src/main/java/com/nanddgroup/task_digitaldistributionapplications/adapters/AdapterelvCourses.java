package com.nanddgroup.task_digitaldistributionapplications.adapters;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import com.nanddgroup.task_digitaldistributionapplications.FilterParams;
import com.nanddgroup.task_digitaldistributionapplications.IConstants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikita on 07.01.2017.
 */

public class AdapterelvCourses {
    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";
    String[] groups = new String[]{"Course"};
    String[] phonesHTC = new String[]{FilterParams.NONE_COURSE,
            IConstants.DB.STUDENT_NAME_COURSE_0,
            IConstants.DB.STUDENT_NAME_COURSE_1,
            IConstants.DB.STUDENT_NAME_COURSE_2,
            IConstants.DB.STUDENT_NAME_COURSE_3};
    ArrayList<Map<String, String>> groupData;
    ArrayList<Map<String, String>> childDataItem;
    ArrayList<ArrayList<Map<String, String>>> childData;
    Map<String, String> m;
    Context ctx;
    SimpleExpandableListAdapter adapter;

    public AdapterelvCourses(Context _ctx) {
        ctx = _ctx;
    }

    public SimpleExpandableListAdapter getAdapter() {

        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups) {
            m = new HashMap<String, String>();
            m.put(ATTR_GROUP_NAME, group);
            groupData.add(m);
        }
        String groupFrom[] = new String[]{ATTR_GROUP_NAME};
        int groupTo[] = new int[]{android.R.id.text1};
        childData = new ArrayList<ArrayList<Map<String, String>>>();
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesHTC) {
            m = new HashMap<String, String>();
            m.put(ATTR_PHONE_NAME, phone); // название телефона
            childDataItem.add(m);
        }
        childData.add(childDataItem);
        String childFrom[] = new String[]{ATTR_PHONE_NAME};
        int childTo[] = new int[]{android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(
                ctx,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                groupFrom,
                groupTo,
                childData,
                android.R.layout.simple_list_item_1,
                childFrom,
                childTo);

        return adapter;
    }

    String getGroupText(int groupPos) {
        return ((Map<String, String>) (adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText(int groupPos, int childPos) {
        return ((Map<String, String>) (adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText(int groupPos, int childPos) {
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }
}
