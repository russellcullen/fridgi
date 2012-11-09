#! /bin/bash
mongod > /dev/null &
MONGOD_PID=$!
python app.py
kill -9 ${MONGOD_PID}
