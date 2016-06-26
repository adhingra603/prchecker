#!/bin/bash

#
# Builds the prchecker application in a Docker container
#

CONTAINER=prchecker
IMAGE=adhingra603/prchecker:dev

docker stop $CONTAINER

docker rm $CONTAINER

docker build --rm -t $IMAGE .

docker run --name $CONTAINER $IMAGE


