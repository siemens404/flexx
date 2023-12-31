// This is a Flexxgram source code file.
// Flexxgram is not a trademark of Telegram and Telegram X.
// Flexxgram is an open-source and freely distributed modification of Telegram X.
//
// Copyright (C) 2023 Flexxteam.

package com.flexxteam.messenger.ui.controllers;

import android.content.Context;
import android.view.View;

import org.thunderdog.challegram.R;
import org.thunderdog.challegram.component.base.SettingView;
import org.thunderdog.challegram.core.Lang;
import org.thunderdog.challegram.telegram.Tdlib;
import org.thunderdog.challegram.ui.ListItem;
import org.thunderdog.challegram.ui.RecyclerViewController;
import org.thunderdog.challegram.ui.SettingsAdapter;
import org.thunderdog.challegram.v.CustomRecyclerView;

import java.util.ArrayList;

import com.flexxteam.messenger.FlexxConfig;

public class AppearanceSettingsController extends RecyclerViewController<Void> implements View.OnClickListener {

  private SettingsAdapter adapter;

  public AppearanceSettingsController (Context context, Tdlib tdlib) {
    super(context, tdlib);
  }

  @Override public CharSequence getName () {
    return Lang.getString(R.string.AppearanceSettingsController);
  }

  @Override public int getId () {
    return R.id.controller_appearanceSettings;
  }

  @Override public void onClick (View v) {
    int viewId = v.getId();
    if (viewId == R.id.btn_hidePhoneNumber) {
      FlexxConfig.instance().toggleHidePhoneNumber();
      adapter.updateValuedSettingById(R.id.btn_hidePhoneNumber);
    } else if (viewId == R.id.btn_enableChatFolders) {
      FlexxConfig.instance().toggleEnableChatFolders();
      adapter.updateValuedSettingById(R.id.btn_enableChatFolders);
    }
  }

  @Override protected void onCreateView (Context context, CustomRecyclerView recyclerView) {
    adapter = new SettingsAdapter(this) {
      @Override protected void setValuedSetting (ListItem item, SettingView view, boolean isUpdate) {
        view.setDrawModifier(item.getDrawModifier());
        int itemId = item.getId();
        if (itemId == R.id.btn_hidePhoneNumber) {
          view.getToggler().setRadioEnabled(FlexxConfig.hidePhoneNumber, isUpdate);
        } else if (itemId == R.id.btn_enableChatFolders) {
          view.getToggler().setRadioEnabled(FlexxConfig.enableChatFolders, isUpdate);
        }
      }
    };

    ArrayList<ListItem> items = new ArrayList<>();

    items.add(new ListItem(ListItem.TYPE_EMPTY_OFFSET_SMALL));
    items.add(new ListItem(ListItem.TYPE_HEADER, 0, 0, R.string.DrawerOptions));

    items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_hidePhoneNumber, 0, R.string.HidePhoneNumber));
    items.add(new ListItem(ListItem.TYPE_SHADOW_BOTTOM));
    items.add(new ListItem(ListItem.TYPE_DESCRIPTION, 0, 0, R.string.HidePhoneNumberDesc));

    items.add(new ListItem(ListItem.TYPE_HEADER, 0, 0, R.string.AppearanceGeneralOptions));

    items.add(new ListItem(ListItem.TYPE_SHADOW_TOP));
    items.add(new ListItem(ListItem.TYPE_RADIO_SETTING, R.id.btn_enableChatFolders, 0, R.string.EnableChatFolders));
    items.add(new ListItem(ListItem.TYPE_SHADOW_BOTTOM));
    items.add(new ListItem(ListItem.TYPE_DESCRIPTION, 0, 0, R.string.EnableChatFoldersDesc));

    adapter.setItems(items, true);
    recyclerView.setAdapter(adapter);
  }
}
