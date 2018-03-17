#!/bin/bash

host=""

while IFS='' read -r line || [[ -n "$line" ]]; do
    host="$line"
done < "$(pwd)/ansible/inventory/pki/$1/host_address"

echo "Using $host"

export DOCKER_TLS_VERIFY="1"
export DOCKER_HOST="tcp://$host:2376"
export DOCKER_CERT_PATH="$(pwd)/ansible/inventory/pki/$1"