# StaffChat

A lightweight PaperMC plugin that adds prefix-based staff and admin chat channels. Staff members can talk to each other privately by prefixing their messages, without leaking into public chat.

## Features

- **Prefix-based channels** — type a prefix in front of your message to route it to a channel.
- **Admin chat** — messages starting with `**` go to admin chat (`erila.admin.chat`).
- **Staff chat** — messages starting with `*` go to staff chat (`erila.staff.chat`).
- **Permission gated** — players without the channel permission send normal chat instead.
- **Configurable** — prefixes, permissions, and message formats are defined in `config.yml`.
- **MiniMessage formatting** — full color and styling support via Adventure MiniMessage.
- **Console mirroring** — channel messages are echoed to the server console.

## How It Works

When a player sends a chat message, the plugin matches it against the registered channels, picking the **longest matching prefix** first (so `**` is checked before `*`). If the sender holds the channel permission, the prefix is stripped and the message is broadcast to every online player with that permission plus the console. Otherwise the message falls through to normal chat.

```
**hello team   -> Admin chat (requires erila.admin.chat)
*on my way     -> Staff chat (requires erila.staff.chat)
hello world    -> Public chat
```

## Commands

| Command             | Aliases | Description                             | Permission              |
|---------------------|---------|-----------------------------------------|-------------------------|
| `/staffchat reload` | `/sc`   | Reload `config.yml` without a restart   | `erila.staffchat.reload`|

## Permissions

| Permission              | Description                          | Default |
|-------------------------|--------------------------------------|---------|
| `erila.admin.chat`      | Send and read admin chat (`**`)      | `op`    |
| `erila.staff.chat`      | Send and read staff chat (`*`)       | `op`    |
| `erila.staffchat.reload`| Reload the plugin configuration      | `op`    |

## Configuration

`config.yml`:

```yaml
channels:
  admin:
    prefix: "**"
    permission: erila.admin.chat
    format: "<dark_red>[AdminChat] <gray><player>: <white><message>"
  staff:
    prefix: "*"
    permission: erila.staff.chat
    format: "<blue>[StaffChat] <gray><player>: <white><message>"
```

Available placeholders in `format`:

- `<player>` — sender name
- `<message>` — message body (prefix stripped)

Any [MiniMessage](https://docs.advntr.dev/minimessage/format.html) tag is supported in `format`.

### Messages

All plugin-generated messages live in `messages.yml` and are MiniMessage-formatted:

```yaml
command:
  usage: "<yellow>Usage: /<label> reload"
  no-permission: "<red>You don't have permission to do that."
  reloaded: "<green>StaffChat configuration reloaded."
log:
  enabled: "StaffChat enabled!"
  disabled: "StaffChat disabled!"
```

`/staffchat reload` reloads both `config.yml` and `messages.yml`.

## Building

Requires JDK 21 and Gradle.

```bash
gradle wrapper --gradle-version 8.8
./gradlew build
```

The compiled plugin jar is produced in `build/libs/`.

## Requirements

- PaperMC 1.21.4 or newer
- Java 21

## License

Released under the [MIT License](LICENSE).
