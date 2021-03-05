#!/bin/sh

exec java \
    -server \
    -Djava.net.preferIPv4Stack=true \
    -Djava.io.tmpdir="${TMPDIR:-/dev/shm}" \
    -jar -Xmx512m boot.jar \
    "$@"
