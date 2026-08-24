#!/usr/bin/env bash
set -euo pipefail

export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH="$JAVA_HOME/bin:$PATH"

DIRETORIO_SCRIPT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "${DIRETORIO_SCRIPT}"

mvn -s settings-sem-nexus.xml "$@"
