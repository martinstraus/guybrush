#!/bin/bash

version=$1
message=$2
username=$3

./release.sh $version $message

if [ "$?" != "0" ]
then
    echo "Couldn't generate version"
    exit 1
fi

./build.sh

if [ "$?" != "0" ]
then
    echo "Couldn't build package"
    exit 2
fi

./upload.sh $username

if [ "$?" != "0" ]
then
    echo "Couldn't upload package"
    exit 3
fi
