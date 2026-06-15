package com.erilanetwork.staffchat.chat.impl;

import com.erilanetwork.staffchat.chat.base.AbstractChatChannel;

public class StaffChatChannel extends AbstractChatChannel {

    public StaffChatChannel(String prefix, String permission, String format) {
        super("staff", prefix, permission, format);
    }
}
