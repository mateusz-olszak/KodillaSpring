#!/usr/bin/env bash

fail(){
 echo "Execution failed."
}


if ./runcrud.sh; then
 open -a "Google Chrome" http://localhost:8080/crud/v1/task/getTasks
else
 fail
fi
