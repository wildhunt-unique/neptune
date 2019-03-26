#!/usr/bin/env bash
if [ $# != 1 ]; then
	echo "please input your version $0"
	exit 1
fi

echo "confirm your version, y/n"

read choice

if [ "$choice" != "y" ] && [ "$choice" != "Y" ]; then
    echo "exit with choice: $choice"
	exit 1
fi

mvn versions:set -DgenerateBackupPoms=false -DnewVersion=$1
exit 0