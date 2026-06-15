package com.erilanetwork.staffchat.chat.impl;

import com.erilanetwork.staffchat.chat.base.AbstractChatChannel;

public class AdminChatChannel extends AbstractChatChannel {

    public AdminChatChannel(String prefix, String permission, String format) {
        super("admin", prefix, permission, format);
    }
}
