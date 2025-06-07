#!/bin/bash
set -e
if ! command -v g++ >/dev/null; then
    echo "g++ not found, attempting to install"
    sudo apt-get update
    sudo apt-get install -y build-essential
fi
which g++
g++ --version
