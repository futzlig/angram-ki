#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

if [ ! -f target/anagram-cli-1.0.0.jar ]; then
  ./build.sh
fi

java -Dfile.encoding=UTF-8 -jar target/anagram-cli-1.0.0.jar
