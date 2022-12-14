#!/usr/bin/env bash

echo "Running pre-commit hook"
sh scripts/runUnitTest.sh

# $? stores exit value of the last command
if [ $? -ne 0 ]; then
 echo "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
 echo "------------------------------------------- THERE ARE TEST FAILURES. TESTS MUST PASS BEFORE COMMIT! --------------------------------------------"
 echo "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
 exit 1
fi