#!/bin/bash

function usage() {
    echo "Usage"
    echo "   ./upload.sh [username]"
}

username=$1

if [ -z "${username}" ]
then
    usage
    exit 1
fi

echo "put ./build/distributions/guybrush.deb /tmp" | sftp ${username}@martinstraus.com.ar
