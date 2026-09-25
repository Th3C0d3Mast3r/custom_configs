#!/usr/bin/env bash

set -euo pipefail

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

if [[ -f /etc/os-release ]]; then
  . /etc/os-release
else
  echo "Unable to detect the Linux distribution." >&2
  exit 1
fi

if [[ "${ID:-}" != "ubuntu" ]]; then
  echo "This bootstrap script is currently intended for Ubuntu." >&2
  echo "Detected distribution: ${PRETTY_NAME:-unknown}" >&2
  exit 1
fi

sudo apt update
sudo apt install -y ansible

cd "$SCRIPT_DIR"
ansible-playbook site.yml --ask-become-pass "$@"