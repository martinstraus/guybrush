#!/bin/bash

/opt/jdk21/bin/java \
    -Xmx32M -XX:MaxRAM=64M \
    -Dspring.config.additional-location=/etc/guybrush/application.properties \
    -jar /opt/guybrush/guybrush.jar \
    --server.port=8080