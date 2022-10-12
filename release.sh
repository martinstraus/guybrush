#!/bin/bash

function usage() {
    echo "Usage"
    echo "   ./release.sh VV.vv.rr \"Version description\""
}

version=$1
message=$2

if [ -z "${version}" ]
then
    usage
    exit 1
fi

if [ -z "${message}" ]
then
    usage
    exit 1
fi


mvn versions:set -DnewVersion=${version}

mvn clean install -Prelease

if [ "$?" != "0" ]
then 
    mvn versions:revert
    exit 2
fi

mvn versions:commit
git add pom.xml
git commit -m "Changed version to ${version}."
git tag -a v${version} -m ${message}
git push 
git push --tags origin
