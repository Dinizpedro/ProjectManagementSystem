#!/bin/bash
USERS_IN_SERVER=/var/www/dataManagament/userStore
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
    eval "$(jq -r '@sh "EMAIL_JSON=\(.email) PASSWORD_JSON=\(.password) CODE_JSON=\(.projectCode)"')"
else
    error_response "No content found on the request"
fi

if [ "$CONTENT_LENGTH" == "0" ]; then
    error_response "No content found on the request"
fi

### If the wrong method is used
if [ "$REQUEST_METHOD" != "POST" ]; then
    error_response "Invalid method. The only supported method is POST."
fi

### Check if user exists and has the Director profile
EMAIL_FOUND=0
if [ -d "$USERS_IN_SERVER/$EMAIL_JSON" ]; then
    EMAIL_FOUND=1
fi

if [ $EMAIL_FOUND -eq 0 ]; then
    error_response "Director email doesn't exist in server"
fi

HAS_DIRECTOR_PROFILE=0
if [ -f "$USERS_IN_SERVER/$EMAIL_JSON/userProfiles/Director" ] ; then
    HAS_DIRECTOR_PROFILE=1
fi

if [ $HAS_DIRECTOR_PROFILE -eq 0 ]; then
    error_response "Email inserted doesn't have the Director profile associated."
fi

### Hash current password and compare with the one stored in the system
PASS_MATCH=0
CURRENT_HASH=$(cat "$USERS_IN_SERVER"/"$EMAIL_JSON"/pass)
OLD_HASH=$((0x$(sha1sum <<<"$PASSWORD_JSON")0))
if [ "$CURRENT_HASH" = "$OLD_HASH" ]; then
  PASS_MATCH=1
fi

if [ $PASS_MATCH -eq 0 ]; then
  error_response "Wrong password."
fi

### Check if project exists
PROJECTCODE_FOUND=0
if [ -d "$PROJECTS_IN_SERVER/$CODE_JSON" ]; then
    PROJECTCODE_FOUND=1
fi

if [ $PROJECTCODE_FOUND -eq 0 ]; then
    error_response "Project code do not exists."
fi
###

### Send JASON
STARTDATE=$(cat $PROJECTS_IN_SERVER/$CODE_JSON/startDate)
ENDDATE=$(cat $PROJECTS_IN_SERVER/$CODE_JSON/endDate)
SPRINTDURATION=$(cat $PROJECTS_IN_SERVER/$CODE_JSON/sprintDuration)
NUMBERSPRINTS=$(cat $PROJECTS_IN_SERVER/$CODE_JSON/numberOfPlannedSprints)
STATUS=$(cat $PROJECTS_IN_SERVER/$CODE_JSON/status)
TYPOLOGY=$(cat $PROJECTS_IN_SERVER/$CODE_JSON/typology)

JSON="{"\"startDate\"":"\"$STARTDATE\"",
"\"endDate\"":"\"$ENDDATE\"",
"\"sprintDuration\"":"\"$SPRINTDURATION\"",
"\"numberOfPlannedSprints\"":"\"$NUMBERSPRINTS\"",
"\"status\"":"\"$STATUS\"",
"\"typology\"":"\"$TYPOLOGY\""}"

echo "Content-type: application/json"
echo "Access-Control-Allow-Orign: *"
echo ""
echo "$JSON"
###
