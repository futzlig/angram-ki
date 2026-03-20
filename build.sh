#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

rm -rf target
mkdir -p target/classes
find src/main/java -name '*.java' | xargs javac -encoding UTF-8 -d target/classes
printf 'Main-Class: de.example.anagramcli.app.AnagramCliApplication\n' > target/manifest.mf
jar cfm target/anagram-cli-1.0.0.jar target/manifest.mf -C target/classes .

echo "Built target/anagram-cli-1.0.0.jar"
