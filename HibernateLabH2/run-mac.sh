#!/bin/bash
# Downloads Java 17 + Maven into ~/hibernate-lab-tools (no admin rights, no Homebrew) and runs the program.
set -e
TOOLS="$HOME/hibernate-lab-tools"
mkdir -p "$TOOLS"
cd "$(dirname "$0")"

ARCH=$(uname -m); [ "$ARCH" = "arm64" ] && JARCH=aarch64 || JARCH=x64

if [ ! -d "$TOOLS/jdk" ]; then
  echo "Downloading Java 17..."
  curl -L -o "$TOOLS/jdk.tar.gz" "https://api.adoptium.net/v3/binary/latest/17/ga/mac/$JARCH/jdk/hotspot/normal/eclipse"
  mkdir -p "$TOOLS/jdk" && tar -xzf "$TOOLS/jdk.tar.gz" -C "$TOOLS/jdk" --strip-components=1
fi
if [ ! -d "$TOOLS/maven" ]; then
  echo "Downloading Maven..."
  curl -L -o "$TOOLS/maven.tar.gz" "https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz"
  mkdir -p "$TOOLS/maven" && tar -xzf "$TOOLS/maven.tar.gz" -C "$TOOLS/maven" --strip-components=1
fi

export JAVA_HOME="$TOOLS/jdk/Contents/Home"
export PATH="$JAVA_HOME/bin:$TOOLS/maven/bin:$PATH"
java -version
mvn -q compile exec:java
