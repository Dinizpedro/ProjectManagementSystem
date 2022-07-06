#!/bin/bash
PROJECT_STATUSES_IN_SERVER=/var/www/dataManagament/projectStatusStore
error_response() {
  echo "Status: 400 Bad Request"
  echo "Content-type: text/plain"
  echo ""
  echo "ERROR: ${1}"
  exit
}
### If the wrong method is used
if [ "$REQUEST_METHOD" != "GET" ]; then
  error_response "Invalid method. The only supported method is GET."
fi

### Check profiles in system

echo "Content-type: text/plain"
echo ""
ls $PROJECT_STATUSES_IN_SERVER | jq -R -s -c 'split("\n")[:-1]'
###
