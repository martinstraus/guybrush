#!/bin/bash

/opt/jdk20/bin/java \
    -Xmx64M -XX:MaxRAM=64M \
    -Dspring.config.additional-location=/etc/guybrush/application.properties \
    -jar /opt/guybrush/guybrush.jar \
    --server.port=8080