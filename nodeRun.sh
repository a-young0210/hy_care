#!/bin/bash

$(nvm install v14)
$(npm install express)

$(npm i express ejs)
$(npm install socket.io@^2.3.0)

$(npm i --save-dev nodemon)
$(npm i -g peer)
$(npm run devStart)