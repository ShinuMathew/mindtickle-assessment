#!/usr/bin/env bash

GIT_DIR=$(git rev-parse --git-dir)

echo "Installing hooks..."

cp scripts/pre-commit.sh $GIT_DIR/hooks/pre-commit
chmod +x $GIT_DIR/hooks/pre-commit
# cp pre-commit.sh $GIT_DIR/hooks/pre-commit
# chmod +x $GIT_DIR/hooks/pre-commit

echo "Done"!