# Linux

This directory is intended to become the Linux automation layer for this repo.
The target platform for now is **Ubuntu 24.04 LTS**. The idea is to keep the setup reproducible with Ansible: one command to bootstrap the machine, then small focused playbooks that you can keep extending over time.

## Goal

The structure here is meant to cover three things:

- machine bootstrap
- package and app installation
- personal configuration and later ricing

The actual package lists, dotfiles, themes, and app choices can be filled in later. For now, this gives a clean base layout.

## Single Setup Command

From the repo root, run:

```bash
bash Linux/ansible/setup.sh
```

What this does:

1. checks that the machine is Ubuntu
2. installs Ansible through `apt`
3. runs the main playbook against `localhost`

## Suggested Layout

The Ansible files live under `Linux/ansible`.

```text
Linux/
├── README.md
└── ansible/
	├── ansible.cfg
	├── inventory/
	│   └── hosts.ini
	├── setup.sh
	├── site.yml
	└── playbooks/
		├── bootstrap.yml
		├── base.yml
		├── shell.yml
		├── dev.yml
		├── desktop.yml
		├── services.yml
		└── post_setup.yml
```

## What Each Playbook Should Own

### `bootstrap.yml`

Use this for machine-level prerequisites that must exist before the rest of the setup works.

Examples:

- apt update/upgrade flow
- extra apt repositories
- package signing keys
- basic tools needed by later playbooks

### `base.yml`

Use this for the common baseline every machine should get.

Examples:

- CLI utilities
- fonts
- archive tools
- clipboard tools
- basic system preferences

### `shell.yml`

Use this for terminal and shell experience.

Examples:

- zsh or bash preferences
- terminal emulator config
- tmux
- aliases
- prompt/theme setup

### `dev.yml`

Use this for development tooling.

Examples:

- Git and Git config
- Docker
- language runtimes
- build tools
- VS Code or other editors

### `desktop.yml`

Use this for GUI apps and later ricing work.

Examples:

- browsers
- media apps
- launcher and bar setup
- wallpapers
- GTK/QT themes
- icons, cursors, compositor, window manager tweaks

This is the file that can later split further if the ricing section becomes large.

### `services.yml`

Use this for long-running background behavior.

Examples:

- SSH setup
- Syncthing
- Docker service tweaks
- user services
- systemd units

### `post_setup.yml`

Use this for the final pass.

Examples:

- verification checks
- cleanup tasks
- reminders for manual steps
- reboot/log out prompts

## Order of Execution

`site.yml` imports the playbooks in this order:

1. `bootstrap.yml`
2. `base.yml`
3. `shell.yml`
4. `dev.yml`
5. `desktop.yml`
6. `services.yml`
7. `post_setup.yml`

That order keeps the setup predictable: prerequisites first, user experience later, verification at the end.

## Recommended Workflow

1. Put only the essential machine-wide prerequisites in `bootstrap.yml`.
2. Keep package installation grouped by purpose instead of one huge file.
3. Keep ricing inside `desktop.yml` first, then split it later only if it becomes too large.
4. Put anything risky or environment-specific behind clear task names and tags.
5. Keep manual checks documented in `post_setup.yml` and this README.

## Manual Steps Before Running

Before running the setup, verify these things manually:

1. You are on Ubuntu 24.04 LTS.
2. Your user has `sudo` access.
3. You know which desktop session or window manager you are targeting.
4. You have checked any existing defaults that could conflict with the final config.

## Nice Next Additions Later

When you start filling in the real config, the next useful upgrades would be:

- `group_vars/all.yml` for shared variables
- `host_vars/localhost.yml` for machine-specific overrides
- `roles/` if any playbook grows too large
- tags such as `base`, `dev`, `desktop`, and `services`

> [!NOTE]
> Different desktop environments and defaults can break automation in subtle ways. Keep the first version conservative, especially around display manager, theme, shell, and session-specific settings.