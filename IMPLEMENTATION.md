# Implementation Guide for the Linux Ansible Setup

This document explains what to include in the Ansible-based setup for this repository and how to keep it clean, reusable, and safe to run again on a new machine.

## Goal

The point of this repo is to create a repeatable configuration system: one command should bootstrap a new Ubuntu machine with the tools and preferences you normally use. The setup should feel familiar and ready to work within minutes.

## Target platform

Current target: Ubuntu 24.04 LTS.

Keep the first version conservative and compatible with standard Ubuntu workstation setups. Avoid making assumptions about desktop environment, shell, or theme too early.

## Recommended repo structure

```text
Linux/
├── README.md
├── TODO.md
├── Implementation.md
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

## What each part should contain

### bootstrap.yml

This is the system bootstrap layer.

Add things like:
- apt update / upgrade flow
- apt repositories and keys
- package signing setup
- core dependencies for later tasks
- base OS prerequisites

Keep this focused and limited to things that must exist before the rest of the setup.

### base.yml

This is the common baseline for all installs.

Include:
- CLI utilities
- compression tools
- file utilities
- general environment tools
- package sets that every machine should have

Good examples:
- curl
- git
- unzip
- jq
- htop
- ripgrep
- tmux
- ca-certificates

### shell.yml

This is for your terminal experience.

Include:
- zsh or bash config
- aliases
- prompt theme
- terminal utilities
- shell startup files
- tmux or other terminal workflow tools

Keep it easy to rerun and avoid brittle shell assumptions.

### dev.yml

This is the development layer.

Include:
- Git config
- Docker
- language runtimes
- package managers
- build tools
- code editors
- developer CLI tools

Examples:
- git
- docker.io
- python3
- python3-pip
- build-essential
- VS Code or editor config
- runtime managers such as nvm or asdf

### desktop.yml

This is the graphical and visual layer.

Include:
- browser apps
- media apps
- desktop environment tools
- themes and icon sets
- wallpaper config
- launcher or panel tweaks
- GNOME/KDE/Xfce specific customizations

This is usually the biggest part, and it is fine to split it later if it grows too much.

### services.yml

This is for long-running or background services.

Include:
- SSH setup
- Syncthing
- Docker service config
- systemd units
- user-level background tasks

Do not put all personal preferences here. Services should be operational, not aesthetic.

### post_setup.yml

This is the verification and final pass.

Include:
- checks that critical tools exist
- reminders for manual steps
- cleanup actions
- reboot or logout prompts
- final validation output

This is where you tell the user what still needs attention after the automation is done.

## How to write Ansible well

### 1. Keep one responsibility per playbook

Do not mix package installation, shell config, and desktop theming in one giant playbook. Split by purpose.

### 2. Use idempotent tasks

Every task should be safe to rerun. That means a second run should not fail or create duplicates.

### 3. Use variables instead of hardcoded values

Create a shared values file such as:
- group_vars/all.yml
- host_vars/localhost.yml

This makes edits easier and reduces repeated package lists across playbooks.

### 4. Add tags

Use tags like:
- base
- shell
- dev
- desktop
- services
- verify

This makes it much easier to test only a part of the setup.

### 5. Prefer clear task names

Bad:
- apt install stuff

Good:
- Install core CLI utilities
- Configure default zsh environment
- Install Docker and Docker Compose

Clear task names make maintenance much easier.

### 6. Keep machine-specific assumptions explicit

Do not assume:
- the same shell everywhere
- the same desktop environment everywhere
- the same package names across all Ubuntu versions
- the same user-specific preferences on every machine

If a setting is not universal, put it behind a variable or keep it in a machine-specific file.

### 7. Keep risky tasks documented

Some actions are not always safe to automate completely:
- GUI theming
- desktop session changes
- display manager setup
- service restarts on live systems
- shell replacements that affect the logged-in environment

They should be explicit, tested, and ideally documented in the final check stage.

## Good implementation pattern

The clean formula is:

1. bootstrap prerequisites
2. add shared base tools
3. install shell and dev environment
4. add desktop layer
5. enable services
6. verify and hand off

This gives you a stable, predictable flow.

## What to look out for

### OS assumptions

This repo is Ubuntu-first, so keep the setup tightly aligned with Ubuntu 24.04. Do not assume Debian or Fedora instructions will work the same way.

### Desktop environment mismatch

Different DEs often use different config files, package names, and session behavior. Avoid forcing one environment onto all machines.

### Secret and identity data

Never store:
- API tokens
- SSH private keys
- personal credentials
- cloud account details

in the repo.

Instead, generate or import them manually or use a secure secret-management method.

### Package churn

Do not install every possible app in one pass. Start with the essentials, then add more as you truly need them.

### Tying too much to a single machine

A config repo should be reusable. Keep the values general enough to work across multiple fresh setups while still allowing per-host overrides.

### Manual steps forgotten

If a setup step cannot be automated cleanly, record it in `post_setup.yml` or the project README so it is not lost.

## Suggested first-pass checklist

Before running the setup on a new machine, verify:
- the machine is Ubuntu 24.04 LTS
- the user has sudo access
- the desktop target is known
- SSH keys and GitHub identity are ready
- package groups are planned and useful

## Final recommendation

The best version of this project is one that is:
- modular
- easy to rerun
- easy to debug
- explicit about manual steps
- conservative on desktop assumptions
- clearly separated by purpose

This makes the repo useful not just right now, but also when you set up a new device later and want a near-copy of your personal workflow.

## Minimal setup command

```bash
bash Linux/ansible/setup.sh
```

This should remain your single entry point.
