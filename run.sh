#! /bin/bash
mongod > /dev/null &
MONGOD_PID=$!
echo "Mongo DB Started"
echo "fridgi running on localhost:5000"
python app.py
kill -9 ${MONGOD_PID}
