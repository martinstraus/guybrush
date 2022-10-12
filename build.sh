#!/bin/bash

mvn clean package -Prelease

if [ "$?" != "0" ]
then
    echo "Build failed"
    exit 1
fi

make

if [ "$?" != "0" ]
then
    echo "Build failed"
    exit 1
fi
