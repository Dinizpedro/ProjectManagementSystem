#!/bin/bash
PROJECTS_IN_SERVER=/var/www/dataManagament/projectStore
error_response() {
    echo "Status: 400 Bad Request"
    echo "Content-type: text/plain"
    echo ""
    echo "ERROR: ${1}"
    rm -f $M_CONTENT_FILE
    exit
}

### Get content from JSON into global variables
if [ -n "$CONTENT_LENGTH" ]; then
    eval "$(jq -r '@sh "CODE_JSON=\(.projectCode)"')"
else
    error_response "No content found on the request"
fi

if [ "$CONTENT_LENGTH" == "0" ]; then
    error_response "No content found on the request"
fi

### Check if project exists
PROJECTCODE_FOUND=0
if [ -d "$PROJECTS_IN_SERVER/$CODE_JSON" ]; then
   PROJECTCODE_FOUND=1
fi

if [ $PROJECTCODE_FOUND -eq 1 ]; then
  error_response "Project code already exists."
fi
###

### If the wrong method is used
if [ "$REQUEST_METHOD" != "GET" ]; then
  error_response "Invalid method. The only supported method is GET."
fi

### Check profiles in system

echo "Content-type: text/plain"
echo ""
ls $PROFILES_IN_SERVER | jq -R -s -c 'split("\n")[:-1]'
###
